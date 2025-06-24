package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.OrderDocument;
import bdavanzadas.lab1.documentServices.OrderDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents/order")
public class OrderDocumentController {

    private final OrderDocumentService orderService;

    @Autowired
    public OrderDocumentController(OrderDocumentService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDocument>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDocument> getById(@PathVariable String id) {
        return orderService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<OrderDocument>> getByClientId(@PathVariable String clientId) {
        return ResponseEntity.ok(orderService.getByClientId(clientId));
    }

    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<OrderDocument>> getByDealerId(@PathVariable String dealerId) {
        return ResponseEntity.ok(orderService.getByDealerId(dealerId));
    }

    @GetMapping("/exists/{orderId}")
    public ResponseEntity<Boolean> existsByOrderId(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderService.existsByOrderId(orderId));
    }

    @GetMapping("/order-id/{orderId}")
    public ResponseEntity<OrderDocument> getByOrderId(@PathVariable Integer orderId) {
        return orderService.getByOrderId(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderDocument> create(@RequestBody OrderDocument order) {
        return ResponseEntity.ok(orderService.save(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
