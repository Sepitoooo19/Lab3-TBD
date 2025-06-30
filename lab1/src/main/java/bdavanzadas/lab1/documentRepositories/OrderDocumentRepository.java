package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.OrderDocument;
import bdavanzadas.lab1.dtos.RouteCountDTO;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones con documentos de pedidos en MongoDB.
 * Proporciona métodos para consultar y gestionar pedidos del sistema,
 * incluyendo búsquedas por cliente, repartidor y análisis de rutas frecuentes.
 */
@Repository
public interface OrderDocumentRepository extends MongoRepository<OrderDocument, String> {

    /**
     * Busca pedidos asociados a un cliente específico.
     * @param clientId ID del cliente cuyos pedidos se desean consultar.
     * @return Lista de pedidos asociados al cliente.
     */
    List<OrderDocument> findByClientId(String clientId);

    /**
     * Busca pedidos asociados a un repartidor específico.
     * @param dealerId ID del repartidor cuyos pedidos se desean consultar.
     * @return Lista de pedidos asignados al repartidor.
     */
    List<OrderDocument> findByDealerId(Integer dealerId);

    /**
     * Verifica si existe un pedido con el ID especificado.
     * @param orderId ID del pedido a verificar.
     * @return true si existe un pedido con ese ID, false en caso contrario.
     */
    boolean existsByOrderId(Integer orderId);

    /**
     * Busca un pedido por su ID único.
     * @param orderId ID numérico del pedido a buscar.
     * @return Optional que contiene el pedido si existe.
     */
    Optional<OrderDocument> findByOrderId(Integer orderId);

    /**
     * Encuentra la ruta más frecuente para un repartidor desde una fecha específica.
     * @param dealerId ID del repartidor a analizar.
     * @param fromDate Fecha límite inferior para considerar pedidos.
     * @return DTO conteniendo la ruta estimada más frecuente y su conteo.
     * @apiNote La agregación realiza:
     *          1. Filtrado por dealerId y fecha
     *          2. Agrupación por ruta estimada
     *          3. Conteo de ocurrencias
     *          4. Ordenamiento descendente
     *          5. Limite a 1 resultado (el más frecuente)
     */
    @Aggregation(pipeline = {
            "{'$match': { 'dealerId': ?0, 'orderDate': { '$gte': ?1 } }}",
            "{'$group': { '_id': '$estimatedRoute', 'count': { '$sum': 1 } }}",
            "{'$sort': { 'count': -1 }}",
            "{'$limit': 1}"
    })
    RouteCountDTO findMostFrequentRouteByDealerId(Integer dealerId, LocalDateTime fromDate);

    /**
     * Busca pedidos asignados a un repartidor con fecha posterior a la especificada.
     * @param dealerId ID del repartidor.
     * @param date Fecha límite inferior para la búsqueda.
     * @return Lista de pedidos que cumplen con los criterios.
     */
    List<OrderDocument> findByDealerIdAndOrderDateAfter(Integer dealerId, LocalDateTime date);
}