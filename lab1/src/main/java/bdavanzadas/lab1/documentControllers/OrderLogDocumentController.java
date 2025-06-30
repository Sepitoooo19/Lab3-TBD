package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.OrderLogDocument;
import bdavanzadas.lab1.documentServices.OrderLogDocumentService;
import bdavanzadas.lab1.dtos.RapidChangeOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar documentos de registros de pedidos.
 * Proporciona endpoints para crear, consultar y administrar registros de pedidos.
 */
@RestController
@RequestMapping("/documents/order-log")
public class OrderLogDocumentController {

    /**
     * Servicio para operaciones con documentos de registros de pedidos.
     */
    private final OrderLogDocumentService service;

    /**
     * Constructor que inyecta el servicio de documentos de registros de pedidos.
     * @param service Servicio para gestionar documentos de registros de pedidos.
     */
    @Autowired
    public OrderLogDocumentController(OrderLogDocumentService service) {
        this.service = service;
    }

    /**
     * Obtiene todos los registros de pedidos.
     * @return Lista de registros de pedidos con código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<OrderLogDocument>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Obtiene un registro de pedido por su ID.
     * @param orderId ID del registro de pedido.
     * @return Registro de pedido encontrado o NOT FOUND si no existe.
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderLogDocument>> getByOrderId(@PathVariable Integer orderId) {
        return ResponseEntity.ok(service.getByOrderId(orderId));
    }

    /**
     * Crea un nuevo registro de pedido.
     * @param log Documento de registro de pedido a crear.
     * @return Documento de registro de pedido creado con código 200 OK.
     */
    @PostMapping
    public ResponseEntity<OrderLogDocument> create(@RequestBody OrderLogDocument log) {
        return ResponseEntity.ok(service.save(log));
    }

    /**
     * Elimina un registro de pedido por su ID.
     * @param id ID del registro de pedido a eliminar.
     * @return Respuesta sin contenido (204 No Content) si la eliminación fue exitosa.
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene los detalles de los pedidos con cambios rápidos.
     * @return Lista de pedidos con cambios rápidos detallados con código 200 OK.
     */
    @GetMapping("/rapid-change-details")
    public ResponseEntity<List<RapidChangeOrderDTO>> getRapidChangeDetails() {
        return ResponseEntity.ok(service.getOrdersWithRapidChangesDetailed());
    }
}
