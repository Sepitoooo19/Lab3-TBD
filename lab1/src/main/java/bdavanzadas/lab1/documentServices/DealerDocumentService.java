package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.DealerDocument;
import bdavanzadas.lab1.documents.OrderDocument;
import bdavanzadas.lab1.documentRepositories.DealerDocumentRepository;
import bdavanzadas.lab1.documentRepositories.OrderDocumentRepository;
import bdavanzadas.lab1.dtos.RouteCountDTO;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones con repartidores.
 * Proporciona métodos para buscar, crear, actualizar y eliminar repartidores,
 * así como para gestionar sus rutas frecuentes y realizar análisis de rutas.
 */
@Service
public class DealerDocumentService {

    private final DealerDocumentRepository dealerRepository;
    private final OrderDocumentRepository orderRepository;

    /**
     * Constructor que inyecta los repositorios necesarios.
     * @param dealerRepository Repositorio de repartidores
     * @param orderRepository Repositorio de pedidos
     */
    @Autowired
    public DealerDocumentService(DealerDocumentRepository dealerRepository,
                                 OrderDocumentRepository orderRepository) {
        this.dealerRepository = dealerRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Obtiene todos los repartidores registrados.
     * @return Lista de todos los repartidores
     */
    public List<DealerDocument> getAll() {
        return dealerRepository.findAll();
    }

    /**
     * Busca un repartidor por su ID de usuario asociado.
     * @param userId ID del usuario asociado al repartidor
     * @return Optional que contiene el repartidor si existe
     */
    public Optional<DealerDocument> getByUserId(String userId) {
        return dealerRepository.findByUserId(userId);
    }

    /**
     * Busca un repartidor por su RUT único.
     * @param rut RUT del repartidor (formato: 12345678-9)
     * @return Optional que contiene el repartidor si existe
     */
    public Optional<DealerDocument> getByRut(String rut) {
        return dealerRepository.findByRut(rut);
    }

    /**
     * Obtiene solo el nombre de un repartidor por su ID de documento.
     * @param id ID del documento del repartidor
     * @return Nombre del repartidor o null si no existe
     */
    public String getNameById(String id) {
        return dealerRepository.findNameById(id);
    }

    /**
     * Verifica si existe un repartidor con el ID especificado.
     * @param dealerId ID numérico del repartidor
     * @return true si existe, false en caso contrario
     */
    public boolean existsByDealerId(Integer dealerId) {
        return dealerRepository.existsByDealerId(dealerId);
    }

    /**
     * Busca un repartidor por su ID numérico único.
     * @param dealerId ID numérico del repartidor
     * @return Optional que contiene el repartidor si existe
     */
    public Optional<DealerDocument> getByDealerId(Integer dealerId) {
        return dealerRepository.findByDealerId(dealerId);
    }

    /**
     * Guarda o actualiza un repartidor.
     * @param dealer Documento del repartidor a guardar
     * @return Repartidor guardado/actualizado
     */
    public DealerDocument save(DealerDocument dealer) {
        return dealerRepository.save(dealer);
    }

    /**
     * Actualiza la ruta más frecuente de un repartidor basado en sus pedidos recientes.
     * @param dealerId ID del repartidor
     * @param days Número de días hacia atrás para analizar pedidos
     * @return true si se actualizó la ruta, false si no hubo cambios
     * @throws RuntimeException si ocurre algún error durante el proceso
     */
    public boolean updateDealerMostFrequentRoute(Integer dealerId, int days) {
        try {
            Optional<DealerDocument> dealerOpt = dealerRepository.findByDealerId(dealerId);
            if (dealerOpt.isEmpty()) {
                System.err.println("No se encontró el repartidor con ID: " + dealerId);
                return false;
            }

            LocalDateTime fromDate = LocalDateTime.now().minusDays(days);
            RouteCountDTO routeCount = orderRepository.findMostFrequentRouteByDealerId(dealerId, fromDate);

            if (routeCount == null || routeCount.getRoute() == null) {
                System.err.println("No se encontraron rutas para el repartidor: " + dealerId);
                return false;
            }

            Document routeData = routeCount.getRoute();
            if (routeData == null) {
                System.err.println("Formato de ruta inválido para el repartidor: " + dealerId);
                return false;
            }

            String type = routeData.getString("type");
            if (!"LineString".equals(type)) {
                System.err.println("Tipo de geometría no soportado: " + type);
                return false;
            }

            List<List<Double>> coordinates = routeData.get("coordinates", List.class);
            if (coordinates == null || coordinates.isEmpty()) {
                System.err.println("Coordenadas no encontradas para la ruta del repartidor: " + dealerId);
                return false;
            }

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

            GeoJsonLineString route = new GeoJsonLineString(
                    validCoordinates.stream()
                            .map(coords -> new org.springframework.data.geo.Point(coords.get(0), coords.get(1)))
                            .collect(java.util.stream.Collectors.toList())
            );

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
     * Tarea programada para actualizar las rutas frecuentes de todos los repartidores.
     * Se ejecuta diariamente a la medianoche.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateAllDealersFrequentRoutes() {
        List<DealerDocument> dealers = dealerRepository.findAll();
        for (DealerDocument dealer : dealers) {
            updateDealerMostFrequentRoute(dealer.getDealerId(), 7);
        }
    }

    /**
     * Elimina un repartidor por su ID de documento.
     * @param id ID del documento del repartidor
     */
    public void deleteById(String id) {
        dealerRepository.deleteById(id);
    }

    /**
     * Busca un repartidor por su ID de documento.
     * @param id ID del documento del repartidor
     * @return Optional que contiene el repartidor si existe
     */
    public Optional<DealerDocument> getById(String id) {
        return dealerRepository.findById(id);
    }
}