package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.OrderLogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para operaciones con documentos de logs de pedidos en MongoDB.
 * Proporciona métodos para consultar y analizar el historial de cambios de estado
 * de los pedidos del sistema.
 */
@Repository
public interface OrderLogDocumentRepository extends MongoRepository<OrderLogDocument, String> {

    /**
     * Busca todos los registros de log asociados a un pedido específico.
     * @param orderId ID del pedido cuyos logs se desean consultar.
     * @return Lista de registros de log asociados al pedido.
     */
    List<OrderLogDocument> findByOrderId(Integer orderId);

    /**
     * Obtiene todos los registros de log de un pedido ordenados cronológicamente.
     * @param orderId ID del pedido cuyos logs se desean consultar.
     * @return Lista de registros de log ordenados por timestamp ascendente.
     * @apiNote Este método es útil para analizar la secuencia de cambios de estado
     *          de un pedido, particularmente para detectar patrones como múltiples
     *          cambios de estado en cortos períodos de tiempo.
     */
    List<OrderLogDocument> findAllByOrderIdOrderByTimestampAsc(Integer orderId);
}