package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.OrderDocument;
import bdavanzadas.lab1.documentRepositories.OrderDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDocumentService {

    private final OrderDocumentRepository orderRepository;

    @Autowired
    public OrderDocumentService(OrderDocumentRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDocument> getByClientId(String clientId) {
        return orderRepository.findByClientId(clientId);
    }

    public List<OrderDocument> getByDealerId(String dealerId) {
        return orderRepository.findByDealerId(dealerId);
    }

    public boolean existsByOrderId(Integer orderId) {
        return orderRepository.existsByOrderId(orderId);
    }

    public Optional<OrderDocument> getByOrderId(Integer orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public Optional<OrderDocument> getById(String id) {
        return orderRepository.findById(id);
    }

    public OrderDocument save(OrderDocument order) {
        return orderRepository.save(order);
    }

    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    public List<OrderDocument> getAll() {
        return orderRepository.findAll();
    }
}
