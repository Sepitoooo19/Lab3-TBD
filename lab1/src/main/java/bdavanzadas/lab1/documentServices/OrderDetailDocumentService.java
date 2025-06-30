package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.OrderDetailDocument;
import bdavanzadas.lab1.documentRepositories.OrderDetailDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones con detalles de órdenes.
 * Proporciona métodos para buscar, crear, actualizar y eliminar detalles de órdenes,
 * así como para verificar la existencia de detalles por su ID.
 */
@Service
public class OrderDetailDocumentService {

    /**
     * Repositorio para acceder a los documentos de detalles de órdenes.
     * Este repositorio permite realizar operaciones CRUD sobre los detalles de órdenes.
     */
    private final OrderDetailDocumentRepository orderDetailRepository;

    /**
     * Constructor que inyecta el repositorio de detalles de órdenes.
     * @param orderDetailRepository Repositorio de detalles de órdenes
     */
    @Autowired
    public OrderDetailDocumentService(OrderDetailDocumentRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    /**
     * Obtiene todos los detalles de órdenes asociados a un ID de orden específico.
     * @param orderId ID de la orden para la cual se desean obtener los detalles
     * @return Lista de detalles de órdenes asociados al ID de orden
     */
    public List<OrderDetailDocument> getByOrderId(String orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }


    /**
     * Verifica si existe un detalle de orden con el ID de detalle especificado.
     * @param orderDetailId ID del detalle de orden
     * @return true si existe, false en caso contrario
     */
    public boolean existsByOrderDetailId(Integer orderDetailId) {
        return orderDetailRepository.existsByOrderDetailId(orderDetailId);
    }


    /**
     * Obtiene un detalle de orden por su ID de detalle.
     * @param orderDetailId ID del detalle de orden
     * @return Optional que contiene el detalle de orden si existe
     */
    public Optional<OrderDetailDocument> getByOrderDetailId(Integer orderDetailId) {
        return orderDetailRepository.findByOrderDetailId(orderDetailId);
    }


    /**
     * Obtiene un detalle de orden por su ID de documento.
     * @param id ID del documento del detalle de orden
     * @return Optional que contiene el detalle de orden si existe
     */
    public Optional<OrderDetailDocument> getById(String id) {
        return orderDetailRepository.findById(id);
    }


    /**
     * Guarda o actualiza un detalle de orden.
     * @param detail Detalle de orden a guardar o actualizar
     * @return Detalle de orden guardado/actualizado
     */
    public OrderDetailDocument save(OrderDetailDocument detail) {
        return orderDetailRepository.save(detail);
    }

    /**
     * Elimina un detalle de orden por su ID de documento.
     * @param id ID del documento del detalle de orden
     */

    public void deleteById(String id) {
        orderDetailRepository.deleteById(id);
    }

    /**
     * Obtiene todos los detalles de órdenes registrados en el sistema.
     * @return Lista completa de detalles de órdenes
     */
    public List<OrderDetailDocument> getAll() {
        return orderDetailRepository.findAll();
    }
}
