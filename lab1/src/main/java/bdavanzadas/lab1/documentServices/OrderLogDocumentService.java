package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.OrderLogDocument;
import bdavanzadas.lab1.documentRepositories.OrderLogDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLogDocumentService {

    private final OrderLogDocumentRepository repository;

    @Autowired
    public OrderLogDocumentService(OrderLogDocumentRepository repository) {
        this.repository = repository;
    }

    public List<OrderLogDocument> getByOrderId(Integer orderId) {
        return repository.findByOrderId(orderId);
    }

    public List<OrderLogDocument> getAll() {
        return repository.findAll();
    }

    public OrderLogDocument save(OrderLogDocument log) {
        return repository.save(log);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
