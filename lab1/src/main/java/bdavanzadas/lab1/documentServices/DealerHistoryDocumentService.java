package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.DealerDocument;
import bdavanzadas.lab1.documents.DealerHistoryDocument;
import bdavanzadas.lab1.documents.DealerHistoryDocument.LocationHistory;
import bdavanzadas.lab1.documents.OrderDocument;
import bdavanzadas.lab1.documentRepositories.DealerHistoryDocumentRepository;
import bdavanzadas.lab1.documentRepositories.OrderDocumentRepository;
import bdavanzadas.lab1.projections.DealerFrequentLocationProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servicio para gestionar el historial de ubicaciones de repartidores.
 * Proporciona métodos para registrar y consultar las rutas y ubicaciones
 * de los repartidores, así como para analizar patrones de movimiento.
 */
@Service
public class DealerHistoryDocumentService {

    private final DealerHistoryDocumentRepository repository;
    private final DealerDocumentService dealerService;
    private final OrderDocumentRepository orderRepository;

    /**
     * Constructor que inyecta las dependencias necesarias.
     * @param repository Repositorio de historial de repartidores
     * @param dealerService Servicio de repartidores
     * @param orderRepository Repositorio de pedidos
     */
    @Autowired
    public DealerHistoryDocumentService(DealerHistoryDocumentRepository repository,
                                        DealerDocumentService dealerService,
                                        OrderDocumentRepository orderRepository) {
        this.repository = repository;
        this.dealerService = dealerService;
        this.orderRepository = orderRepository;
    }

    /**
     * Obtiene el historial de ubicaciones de un repartidor específico.
     * @param dealerId ID del repartidor
     * @return Lista de documentos de historial del repartidor
     */
    public List<DealerHistoryDocument> getByDealerId(Integer dealerId) {
        return repository.findByDealerId(dealerId);
    }

    /**
     * Obtiene todo el historial de ubicaciones de repartidores.
     * @return Lista completa de documentos de historial
     */
    public List<DealerHistoryDocument> getAll() {
        return repository.findAll();
    }

    /**
     * Guarda o actualiza un documento de historial de repartidor.
     * @param history Documento de historial a guardar
     * @return Documento de historial guardado
     */
    public DealerHistoryDocument save(DealerHistoryDocument history) {
        return repository.save(history);
    }

    /**
     * Elimina un documento de historial por su ID.
     * @param id ID del documento a eliminar
     */
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    /**
     * Obtiene y analiza las ubicaciones frecuentes de repartidores en los últimos 7 días.
     * @return Lista de proyecciones con las ubicaciones frecuentes
     * @apiNote Este método:
     *          1. Obtiene todos los repartidores
     *          2. Para cada repartidor, obtiene sus pedidos de los últimos 7 días
     *          3. Procesa las rutas estimadas de cada pedido
     *          4. Actualiza el historial de ubicaciones
     *          5. Devuelve las ubicaciones más recientes con conteos
     */
    public List<DealerFrequentLocationProjection> getFrequentLocationsLast7Days() {
        List<DealerDocument> dealers = dealerService.getAll();
        List<DealerFrequentLocationProjection> result = new ArrayList<>();

        if (dealers == null || dealers.isEmpty()) {
            return result;
        }

        LocalDateTime weekAgo = LocalDateTime.now().minusDays(7);

        for (DealerDocument dealer : dealers) {
            try {
                List<OrderDocument> orders = orderRepository.findByDealerIdAndOrderDateAfter(
                        dealer.getDealerId(),
                        weekAgo
                );

                if (orders == null || orders.isEmpty()) {
                    continue;
                }

                List<DealerHistoryDocument> existingHistory = repository.findByDealerId(dealer.getDealerId());
                DealerHistoryDocument history = !existingHistory.isEmpty() ?
                        existingHistory.get(0) :
                        new DealerHistoryDocument(null, dealer.getDealerId(), new ArrayList<>(), LocalDateTime.now());

                for (OrderDocument order : orders) {
                    if (order.getEstimatedRoute() != null && order.getOrderId() != null) {
                        for (Point point : order.getEstimatedRoute().getCoordinates()) {
                            GeoJsonPoint geoPoint = new GeoJsonPoint(point.getX(), point.getY());
                            history.addLocation(geoPoint, order.getOrderId().toString());
                        }
                    }
                }

                repository.save(history);

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
     * Método de compatibilidad para mantener la interfaz existente.
     * @param from Fecha desde la cual buscar (no utilizado)
     * @return Lista de proyecciones con las ubicaciones frecuentes
     * @deprecated Reemplazado por {@link #getFrequentLocationsLast7Days()}
     */
    @Deprecated
    public List<DealerFrequentLocationProjection> findFrequentLocationsAfter(Date from) {
        return getFrequentLocationsLast7Days();
    }
}