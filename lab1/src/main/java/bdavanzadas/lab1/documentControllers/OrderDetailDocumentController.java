package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.OrderDetailDocument;
import bdavanzadas.lab1.documentServices.OrderDetailDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar los detalles de los pedidos.
 * Proporciona endpoints para operaciones CRUD sobre los documentos que contienen
 * información detallada de cada pedido en el sistema.
 */
@RestController
@RequestMapping("/documents/order-detail")
public class OrderDetailDocumentController {

    /**
     * Servicio para operaciones con detalles de pedidos.
     */
    private final OrderDetailDocumentService orderDetailService;

    /**
     * Constructor que inyecta el servicio de detalles de pedidos.
     * @param orderDetailService Servicio para gestionar detalles de pedidos.
     */
    @Autowired
    public OrderDetailDocumentController(OrderDetailDocumentService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    /**
     * Obtiene todos los detalles de pedidos registrados en el sistema.
     * @return Lista completa de detalles de pedidos con código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<OrderDetailDocument>> getAll() {
        return ResponseEntity.ok(orderDetailService.getAll());
    }

    /**
     * Obtiene un detalle de pedido por su ID de documento.
     * @param id ID del documento del detalle de pedido.
     * @return Detalle de pedido con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDocument> getById(@PathVariable String id) {
        return orderDetailService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene los detalles asociados a un pedido específico.
     * @param orderId ID del pedido.
     * @return Lista de detalles asociados al pedido con código 200 OK.
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetailDocument>> getByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(orderDetailService.getByOrderId(orderId));
    }

    /**
     * Verifica si existe un detalle de pedido con el ID especificado.
     * @param orderDetailId ID del detalle de pedido a verificar.
     * @return true si existe el detalle de pedido, false en caso contrario.
     */
    @GetMapping("/exists/{orderDetailId}")
    public ResponseEntity<Boolean> existsByOrderDetailId(@PathVariable Integer orderDetailId) {
        return ResponseEntity.ok(orderDetailService.existsByOrderDetailId(orderDetailId));
    }

    /**
     * Obtiene un detalle de pedido por su ID numérico.
     * @param orderDetailId ID numérico del detalle de pedido.
     * @return Detalle de pedido con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/order-detail-id/{orderDetailId}")
    public ResponseEntity<OrderDetailDocument> getByOrderDetailId(@PathVariable Integer orderDetailId) {
        return orderDetailService.getByOrderDetailId(orderDetailId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo detalle de pedido.
     * @param detail Documento del detalle de pedido a crear.
     * @return Detalle de pedido creado con código 200 OK.
     */
    @PostMapping
    public ResponseEntity<OrderDetailDocument> create(@RequestBody OrderDetailDocument detail) {
        return ResponseEntity.ok(orderDetailService.save(detail));
    }

    /**
     * Elimina un detalle de pedido por su ID de documento.
     * @param id ID del documento a eliminar.
     * @return Respuesta sin contenido con código 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        orderDetailService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}