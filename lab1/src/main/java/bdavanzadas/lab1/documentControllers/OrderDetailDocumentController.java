package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.OrderDetailDocument;
import bdavanzadas.lab1.documentServices.OrderDetailDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents/order-detail")
public class OrderDetailDocumentController {

    private final OrderDetailDocumentService orderDetailService;

    @Autowired
    public OrderDetailDocumentController(OrderDetailDocumentService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailDocument>> getAll() {
        return ResponseEntity.ok(orderDetailService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDocument> getById(@PathVariable String id) {
        return orderDetailService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetailDocument>> getByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(orderDetailService.getByOrderId(orderId));
    }

    @GetMapping("/exists/{orderDetailId}")
    public ResponseEntity<Boolean> existsByOrderDetailId(@PathVariable Integer orderDetailId) {
        return ResponseEntity.ok(orderDetailService.existsByOrderDetailId(orderDetailId));
    }

    @GetMapping("/order-detail-id/{orderDetailId}")
    public ResponseEntity<OrderDetailDocument> getByOrderDetailId(@PathVariable Integer orderDetailId) {
        return orderDetailService.getByOrderDetailId(orderDetailId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderDetailDocument> create(@RequestBody OrderDetailDocument detail) {
        return ResponseEntity.ok(orderDetailService.save(detail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        orderDetailService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
