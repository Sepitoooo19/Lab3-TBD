package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.OrderDetailDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones con detalles de pedidos en MongoDB.
 * Proporciona métodos para consultar y gestionar los documentos que contienen
 * información detallada de los pedidos del sistema.
 */
@Repository
public interface OrderDetailDocumentRepository extends MongoRepository<OrderDetailDocument, String> {

    /**
     * Busca los detalles asociados a un pedido específico.
     * @param orderId ID del pedido cuyos detalles se desean consultar.
     * @return Lista de detalles de pedido asociados al orderId especificado.
     *         Retorna lista vacía si no se encuentran detalles.
     */
    List<OrderDetailDocument> findByOrderId(String orderId);

    /**
     * Verifica si existe un detalle de pedido con el ID especificado.
     * @param orderDetailId ID del detalle de pedido a verificar.
     * @return true si existe un detalle con ese ID, false en caso contrario.
     */
    boolean existsByOrderDetailId(Integer orderDetailId);

    /**
     * Busca un detalle de pedido por su ID único.
     * @param orderDetailId ID numérico del detalle de pedido.
     * @return Optional que contiene el detalle de pedido si existe.
     */
    Optional<OrderDetailDocument> findByOrderDetailId(Integer orderDetailId);

}