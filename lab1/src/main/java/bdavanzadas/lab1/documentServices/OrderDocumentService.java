package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.OrderDocument;
import bdavanzadas.lab1.documentRepositories.OrderDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Servicio para gestionar operaciones con documentos de pedidos.
 * Proporciona métodos para buscar, crear, actualizar y eliminar pedidos,
 * así como para consultar información específica sobre ellos.
 */
@Service
public class OrderDocumentService {



    private final OrderDocumentRepository orderRepository;

    /**
     * Constructor que inyecta el repositorio de pedidos.
     * @param orderRepository Repositorio de pedidos a utilizar
     */
    @Autowired
    public OrderDocumentService(OrderDocumentRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Obtiene todos los pedidos registrados en el sistema por un id de cliente.
     * @return Lista de todos los documentos de pedidos.
     *         Retorna lista vacía si no hay pedidos registrados.
     */
    public List<OrderDocument> getByClientId(String clientId) {
        return orderRepository.findByClientId(clientId);
    }

    /**
     * Obtiene todos los pedidos registrados en el sistema por un id de repartidor.
     * @return Lista de todos los documentos de pedidos.
     *         Retorna lista vacía si no hay pedidos registrados.
     */
    public List<OrderDocument> getByDealerId(Integer dealerId) {
        return orderRepository.findByDealerId(dealerId);
    }

    /**
     * Verifica si existe un pedido con el ID de orden especificado.
     * @param orderId ID del pedido
     * @return true si existe, false en caso contrario
     */
    public boolean existsByOrderId(Integer orderId) {
        return orderRepository.existsByOrderId(orderId);
    }

    /**
     * Obtiene un pedido por su ID de orden.
     * @param orderId ID del pedido
     * @return Optional que contiene el pedido si existe
     */
    public Optional<OrderDocument> getByOrderId(Integer orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    /**
     * Obtiene un pedido por su ID de documento.
     * @param id ID del documento del pedido
     * @return Optional que contiene el pedido si existe
     */
    public Optional<OrderDocument> getById(String id) {
        return orderRepository.findById(id);
    }

    /**
     * Guarda o actualiza un pedido.
     * @param order Documento del pedido a guardar
     * @return Pedido guardado
     */
    public OrderDocument save(OrderDocument order) {
        return orderRepository.save(order);
    }

    /**
     * Elimina un pedido por su ID de documento.
     * @param id ID del documento del pedido
     */
    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }


    /**
     * Obtiene todos los pedidos registrados en el sistema.
     * @return Lista completa de documentos de pedidos
     *         Retorna lista vacía si no hay pedidos registrados
     */
    public List<OrderDocument> getAll() {
        return orderRepository.findAll();
    }
}
