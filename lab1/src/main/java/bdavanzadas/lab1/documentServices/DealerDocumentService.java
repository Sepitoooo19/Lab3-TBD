package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.DealerDocument;
import bdavanzadas.lab1.documents.OrderDocument;
import bdavanzadas.lab1.documentRepositories.DealerDocumentRepository;
import java.util.ArrayList;
import java.util.Arrays;
import bdavanzadas.lab1.documentRepositories.OrderDocumentRepository;
import bdavanzadas.lab1.dtos.RouteCountDTO;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DealerDocumentService {

    private final DealerDocumentRepository dealerRepository;

    private final OrderDocumentRepository orderRepository;

    @Autowired
    public DealerDocumentService(DealerDocumentRepository dealerRepository, 
                               OrderDocumentRepository orderRepository) {
        this.dealerRepository = dealerRepository;
        this.orderRepository = orderRepository;
    }

    //get all
    public List<DealerDocument> getAll() {
        return dealerRepository.findAll();
    }


    public Optional<DealerDocument> getByUserId(String userId) {
        return dealerRepository.findByUserId(userId);
    }

    public Optional<DealerDocument> getByRut(String rut) {
        return dealerRepository.findByRut(rut);
    }

    public String getNameById(String id) {
        return dealerRepository.findNameById(id);
    }

    public boolean existsByDealerId(Integer dealerId) {
        return dealerRepository.existsByDealerId(dealerId);
    }

    public Optional<DealerDocument> getByDealerId(Integer dealerId) {
        return dealerRepository.findByDealerId(dealerId);
    }

    public DealerDocument save(DealerDocument dealer) {
        return dealerRepository.save(dealer);
    }
    
    /**
     * Actualiza la ruta más frecuente de un repartidor
     * @param dealerId ID del repartidor
     * @param days Número de días hacia atrás para buscar rutas
     * @return true si se actualizó la ruta, false si no hubo cambios
     */
    public boolean updateDealerMostFrequentRoute(Integer dealerId, int days) {
        try {
            Optional<DealerDocument> dealerOpt = dealerRepository.findByDealerId(dealerId);
            if (dealerOpt.isEmpty()) {
                System.err.println("No se encontró el repartidor con ID: " + dealerId);
                return false;
            }
            
            // Calcular la fecha de inicio (hace X días)
            LocalDateTime fromDate = LocalDateTime.now().minusDays(days);
            
            // Buscar la ruta más frecuente en las órdenes del repartidor
            RouteCountDTO routeCount = orderRepository.findMostFrequentRouteByDealerId(dealerId, fromDate);
            if (routeCount == null || routeCount.getRoute() == null) {
                System.err.println("No se encontraron rutas para el repartidor: " + dealerId);
                return false;
            }

            // Obtener las coordenadas de la ruta
            Document routeData = routeCount.getRoute();
            if (routeData == null) {
                System.err.println("Formato de ruta inválido para el repartidor: " + dealerId);
                return false;
            }

            // Verificar si es un documento GeoJSON
            String type = routeData.getString("type");
            if (!"LineString".equals(type)) {
                System.err.println("Tipo de geometría no soportado: " + type);
                return false;
            }

            // Obtener las coordenadas del GeoJSON
            List<List<Double>> coordinates = routeData.get("coordinates", List.class);
            if (coordinates == null || coordinates.isEmpty()) {
                System.err.println("Coordenadas no encontradas para la ruta del repartidor: " + dealerId);
                return false;
            }
            
            // Validar el formato de las coordenadas
            List<List<Double>> validCoordinates = new ArrayList<>();
            for (List<?> coord : coordinates) {
                if (coord != null && coord.size() >= 2) {
                    try {
                        double x = ((Number) coord.get(0)).doubleValue();
                        double y = ((Number) coord.get(1)).doubleValue();
                        validCoordinates.add(Arrays.asList(x, y));
                    } catch (ClassCastException e) {
                        System.err.println("Formato de coordenadas inválido: " + coord);
                    }
                }
            }
            
            if (validCoordinates.isEmpty()) {
                System.err.println("No se encontraron coordenadas válidas para el repartidor: " + dealerId);
                return false;
            }
            
            coordinates = validCoordinates;

            GeoJsonLineString route = new GeoJsonLineString(
                coordinates.stream()
                    .map(coords -> new org.springframework.data.geo.Point(coords.get(0), coords.get(1)))
                    .collect(java.util.stream.Collectors.toList())
            );
            
            // Actualizar la ruta del repartidor
            DealerDocument dealer = dealerOpt.get();
            boolean updated = dealer.updateMostFrequentRoute(route);
            if (updated) {
                dealerRepository.save(dealer);
                System.out.println("Ruta actualizada para el repartidor: " + dealerId);
            }
            return updated;
        } catch (Exception e) {
            System.err.println("Error al actualizar la ruta del repartidor " + dealerId + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Obtiene la ruta más frecuente de un repartidor
     * @param dealerId ID del repartidor
     * @return La ruta más frecuente o null si no existe
     */
    public GeoJsonLineString getMostFrequentRoute(Integer dealerId) {
        return dealerRepository.findByDealerId(dealerId)
            .map(DealerDocument::getMostFrequentRoute)
            .orElse(null);
    }
    
    /**
     * Tarea programada para actualizar las rutas frecuentes de todos los repartidores
     * Se ejecuta todos los días a la medianoche
     */
    @Scheduled(cron = "0 0 0 * * ?") // Se ejecuta todos los días a la medianoche
    public void updateAllDealersFrequentRoutes() {
        List<DealerDocument> dealers = dealerRepository.findAll();
        for (DealerDocument dealer : dealers) {
            updateDealerMostFrequentRoute(dealer.getDealerId(), 7); // Últimos 7 días
        }
    }

    public void deleteById(String id) {
        dealerRepository.deleteById(id);
    }

    public Optional<DealerDocument> getById(String id) {
        return dealerRepository.findById(id);
    }
}
