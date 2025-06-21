package bdavanzadas.Lab3.services;

import bdavanzadas.Lab3.entities.OrderEntity;
import bdavanzadas.Lab3.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    public Optional<OrderEntity> findById(String id) {
        return orderRepository.findById(id);
    }

    public List<OrderEntity> findByClientId(String clientId) {
        return orderRepository.findByClientId(clientId);
    }

    public List<OrderEntity> findByDealerId(String dealerId) {
        return orderRepository.findByDealerId(dealerId);
    }

    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

    public OrderEntity update(String id, OrderEntity order) {
        order.setId(id);
        return orderRepository.save(order);
    }

    public void delete(String id) {
        orderRepository.deleteById(id);
    }
}
