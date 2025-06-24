package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.OrderLogDocument;
import bdavanzadas.lab1.documentServices.OrderLogDocumentService;
import bdavanzadas.lab1.dtos.RapidChangeOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents/order-log")
public class OrderLogDocumentController {

    private final OrderLogDocumentService service;

    @Autowired
    public OrderLogDocumentController(OrderLogDocumentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OrderLogDocument>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderLogDocument>> getByOrderId(@PathVariable Integer orderId) {
        return ResponseEntity.ok(service.getByOrderId(orderId));
    }

    @PostMapping
    public ResponseEntity<OrderLogDocument> create(@RequestBody OrderLogDocument log) {
        return ResponseEntity.ok(service.save(log));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rapid-change-details")
    public ResponseEntity<List<RapidChangeOrderDTO>> getRapidChangeDetails() {
        return ResponseEntity.ok(service.getOrdersWithRapidChangesDetailed());
    }
}
