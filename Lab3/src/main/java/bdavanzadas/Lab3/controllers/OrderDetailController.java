package bdavanzadas.Lab3.controllers;

import bdavanzadas.Lab3.entities.OrderDetailEntity;
import bdavanzadas.Lab3.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailEntity>> getAll() {
        return ResponseEntity.ok(orderDetailService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailEntity> getById(@PathVariable String id) {
        return orderDetailService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetailEntity>> getByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(orderDetailService.findByOrderId(orderId));
    }

    @PostMapping
    public ResponseEntity<OrderDetailEntity> create(@RequestBody OrderDetailEntity orderDetail) {
        return ResponseEntity.ok(orderDetailService.save(orderDetail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailEntity> update(@PathVariable String id, @RequestBody OrderDetailEntity orderDetail) {
        return ResponseEntity.ok(orderDetailService.update(id, orderDetail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        orderDetailService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
