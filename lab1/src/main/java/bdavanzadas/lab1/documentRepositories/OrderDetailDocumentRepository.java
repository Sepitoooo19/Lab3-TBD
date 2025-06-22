package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.ClientDocument;
import bdavanzadas.lab1.documents.OrderDetailDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailDocumentRepository extends MongoRepository<OrderDetailDocument, String> {
    List<OrderDetailDocument> findByOrderId(String orderId);

    boolean existsByOrderDetailId(Integer orderDetailId);
    Optional<OrderDetailDocument> findByOrderDetailId(Integer orderDetailId);
}
