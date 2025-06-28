package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.DealerDocument;
import bdavanzadas.lab1.documents.DealerHistoryDocument;
import bdavanzadas.lab1.documents.DealerHistoryDocument.LocationHistory;
import bdavanzadas.lab1.documents.OrderDocument;
import bdavanzadas.lab1.documentRepositories.DealerHistoryDocumentRepository;
import bdavanzadas.lab1.documentRepositories.OrderDocumentRepository;
import bdavanzadas.lab1.projections.DealerFrequentLocationProjection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DealerHistoryDocumentService {

    private final DealerHistoryDocumentRepository repository;
    private final DealerDocumentService dealerService;
    private final OrderDocumentRepository orderRepository;

    @Autowired
    public DealerHistoryDocumentService(DealerHistoryDocumentRepository repository, 
                                      DealerDocumentService dealerService,
                                      OrderDocumentRepository orderRepository) {
        this.repository = repository;
        this.dealerService = dealerService;
        this.orderRepository = orderRepository;
    }

    public List<DealerHistoryDocument> getByDealerId(Integer dealerId) {
        return repository.findByDealerId(dealerId);
    }

    public List<DealerHistoryDocument> getAll() {
        return repository.findAll();
    }

    public DealerHistoryDocument save(DealerHistoryDocument history) {
        return repository.save(history);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    /**
     * Obtiene y guarda todas las ubicaciones de las rutas de los repartidores
     * @return Lista de proyecciones con las ubicaciones frecuentes
     */
    public List<DealerFrequentLocationProjection> getFrequentLocationsLast7Days() {
        // Obtener todos los repartidores
        List<DealerDocument> dealers = dealerService.getAll();
        List<DealerFrequentLocationProjection> result = new ArrayList<>();
        
        if (dealers == null || dealers.isEmpty()) {
            return result; // Retornar lista vacía si no hay repartidores
        }
        
        // Fecha de inicio (últimos 7 días)
        LocalDateTime weekAgo = LocalDateTime.now().minusDays(7);
        
        for (DealerDocument dealer : dealers) {
            try {
                // Obtener todas las órdenes del repartidor en los últimos 7 días
                List<OrderDocument> orders = orderRepository.findByDealerIdAndOrderDateAfter(
                    dealer.getDealerId(), 
                    weekAgo
                );
                
                if (orders == null || orders.isEmpty()) {
                    continue; // Saltar si no hay órdenes
                }
                
                // Buscar historial existente o crear uno nuevo
                List<DealerHistoryDocument> existingHistory = repository.findByDealerId(dealer.getDealerId());
                DealerHistoryDocument history = !existingHistory.isEmpty() ? 
                    existingHistory.get(0) : 
                    new DealerHistoryDocument(null, dealer.getDealerId(), new ArrayList<>(), LocalDateTime.now());
                
                // Procesar cada orden para extraer las ubicaciones
                for (OrderDocument order : orders) {
                    if (order.getEstimatedRoute() != null && order.getOrderId() != null) {
                        // Agregar cada punto de la ruta al historial
                        for (Point point : order.getEstimatedRoute().getCoordinates()) {
                            GeoJsonPoint geoPoint = new GeoJsonPoint(point.getX(), point.getY());
                            history.addLocation(geoPoint, order.getOrderId().toString());
                        }
                    }
                }
                
                // Guardar el historial actualizado
                repository.save(history);
                
                // Si hay ubicaciones, agregar la más reciente al resultado
                if (history.getLocations() != null && !history.getLocations().isEmpty()) {
                    LocationHistory lastLocation = history.getLocations().get(history.getLocations().size() - 1);
                    final List<Double> location = List.of(
                        lastLocation.getLocation().getX(), 
                        lastLocation.getLocation().getY()
                    );
                    final Integer dealerId = dealer.getDealerId();
                    
                    result.add(new DealerFrequentLocationProjection() {
                        @Override public Integer getDealerId() { return dealerId; }
                        @Override public List<Double> getLocation() { return location; }
                        @Override public Long getCount() { return (long) history.getLocations().size(); }
                    });
                }
                
            } catch (Exception e) {
                System.err.println("Error procesando repartidor " + dealer.getDealerId() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    /**
     * Método de compatibilidad para mantener la interfaz existente
     * @param from Fecha desde la cual buscar
     * @return Lista de proyecciones con las ubicaciones frecuentes
     * @deprecated Usar getFrequentLocationsLast7Days() en su lugar
     */
    @Deprecated
    public List<DealerFrequentLocationProjection> findFrequentLocationsAfter(Date from) {
        return getFrequentLocationsLast7Days();
    }
}
