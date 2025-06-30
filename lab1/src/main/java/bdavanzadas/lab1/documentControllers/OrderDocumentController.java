package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.OrderDocument;
import bdavanzadas.lab1.documentServices.OrderDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controlador REST para gestionar documentos de pedidos.
 * Proporciona endpoints para crear, consultar y administrar pedidos.
 */
@RestController
@RequestMapping("/documents/order")
public class OrderDocumentController {

    /**
     * Servicio para operaciones con documentos de pedidos.
     */
    private final OrderDocumentService orderService;

    /**
     * Constructor que inyecta el servicio de documentos de pedidos.
     * @param orderService Servicio para gestionar documentos de pedidos.
     */
    @Autowired
    public OrderDocumentController(OrderDocumentService orderService) {
        this.orderService = orderService;
    }

    /**
     * Obtiene todos los documentos de pedidos.
     * @return Lista de documentos de pedidos con código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<OrderDocument>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    /**
     * Obtiene un documento de pedido por su ID.
     * @param id ID del documento de pedido.
     * @return Documento de pedido encontrado o NOT FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderDocument> getById(@PathVariable String id) {
        return orderService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene documentos de pedidos asociados a un cliente específico.
     * @param clientId ID del cliente.
     * @return Lista de documentos de pedidos asociados al cliente con código 200 OK.
     */
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<OrderDocument>> getByClientId(@PathVariable String clientId) {
        return ResponseEntity.ok(orderService.getByClientId(clientId));
    }


    /**
     * Obtiene documentos de pedidos asociados a un distribuidor específico.
     * @param dealerId ID del distribuidor.
     * @return Lista de documentos de pedidos asociados al distribuidor con código 200 OK.
     */
    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<OrderDocument>> getByDealerId(@PathVariable Integer dealerId) {
        return ResponseEntity.ok(orderService.getByDealerId(dealerId));
    }

    /**
     * Verifica si un pedido existe por su ID.
     * @param orderId ID del pedido.
     * @return true si el pedido existe, false en caso contrario.
     */
    @GetMapping("/exists/{orderId}")
    public ResponseEntity<Boolean> existsByOrderId(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderService.existsByOrderId(orderId));
    }

    /**
     * Obtiene un documento de pedido por su ID de pedido.
     * @param orderId ID del pedido.
     * @return Documento de pedido encontrado o NOT FOUND si no existe.
     */
    @GetMapping("/order-id/{orderId}")
    public ResponseEntity<OrderDocument> getByOrderId(@PathVariable Integer orderId) {
        return orderService.getByOrderId(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    /**
     * Crea un nuevo documento de pedido.
     * @param order Documento de pedido a crear.
     * @return Documento de pedido creado con código 200 OK.
     */
    @PostMapping
    public ResponseEntity<OrderDocument> create(@RequestBody OrderDocument order) {
        return ResponseEntity.ok(orderService.save(order));
    }


    /**
     * Elimina un documento de pedido por su ID.
     * @param id ID del documento de pedido a eliminar.
     * @return Respuesta vacía con código 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
