package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.OrderLogDocument;
import bdavanzadas.lab1.documentRepositories.OrderLogDocumentRepository;
import bdavanzadas.lab1.dtos.RapidChangeOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<RapidChangeOrderDTO> getOrdersWithRapidChangesDetailed() {
        Set<Integer> uniqueOrders = repository.findAll()
                .stream()
                .map(OrderLogDocument::getOrderId)
                .collect(Collectors.toSet());

        List<RapidChangeOrderDTO> result = new ArrayList<>();

        for (Integer orderId : uniqueOrders) {
            List<OrderLogDocument> logs = repository.findAllByOrderIdOrderByTimestampAsc(orderId);
            for (int i = 0; i <= logs.size() - 4; i++) {
                LocalDateTime start = logs.get(i).getTimestamp();
                LocalDateTime end = logs.get(i + 3).getTimestamp();
                if (Duration.between(start, end).toMinutes() <= 10) {
                    result.add(new RapidChangeOrderDTO(orderId, logs.size(), start, end));
                    break;
                }
            }
        }
        return result;
    }
}
