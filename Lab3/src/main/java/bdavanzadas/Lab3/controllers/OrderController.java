package bdavanzadas.Lab3.controllers;

import bdavanzadas.Lab3.entities.OrderEntity;
import bdavanzadas.Lab3.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getById(@PathVariable String id) {
        return orderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<OrderEntity>> getByClientId(@PathVariable String clientId) {
        return ResponseEntity.ok(orderService.findByClientId(clientId));
    }

    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<OrderEntity>> getByDealerId(@PathVariable String dealerId) {
        return ResponseEntity.ok(orderService.findByDealerId(dealerId));
    }

    @PostMapping
    public ResponseEntity<OrderEntity> create(@RequestBody OrderEntity order) {
        return ResponseEntity.ok(orderService.save(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> update(@PathVariable String id, @RequestBody OrderEntity order) {
        return ResponseEntity.ok(orderService.update(id, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
