package bdavanzadas.Lab3.services;

import bdavanzadas.Lab3.entities.OrderDetailEntity;
import bdavanzadas.Lab3.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetailEntity> findAll() {
        return orderDetailRepository.findAll();
    }

    public Optional<OrderDetailEntity> findById(String id) {
        return orderDetailRepository.findById(id);
    }

    public List<OrderDetailEntity> findByOrderId(String orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    public OrderDetailEntity save(OrderDetailEntity orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public OrderDetailEntity update(String id, OrderDetailEntity orderDetail) {
        orderDetail.setId(id);
        return orderDetailRepository.save(orderDetail);
    }

    public void delete(String id) {
        orderDetailRepository.deleteById(id);
    }
}
