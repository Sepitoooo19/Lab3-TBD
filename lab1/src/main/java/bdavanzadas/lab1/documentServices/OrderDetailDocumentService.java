package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.OrderDetailDocument;
import bdavanzadas.lab1.documentRepositories.OrderDetailDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailDocumentService {

    private final OrderDetailDocumentRepository orderDetailRepository;

    @Autowired
    public OrderDetailDocumentService(OrderDetailDocumentRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    public List<OrderDetailDocument> getByOrderId(String orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    public boolean existsByOrderDetailId(Integer orderDetailId) {
        return orderDetailRepository.existsByOrderDetailId(orderDetailId);
    }

    public Optional<OrderDetailDocument> getByOrderDetailId(Integer orderDetailId) {
        return orderDetailRepository.findByOrderDetailId(orderDetailId);
    }

    public Optional<OrderDetailDocument> getById(String id) {
        return orderDetailRepository.findById(id);
    }

    public OrderDetailDocument save(OrderDetailDocument detail) {
        return orderDetailRepository.save(detail);
    }

    public void deleteById(String id) {
        orderDetailRepository.deleteById(id);
    }

    public List<OrderDetailDocument> getAll() {
        return orderDetailRepository.findAll();
    }
}
