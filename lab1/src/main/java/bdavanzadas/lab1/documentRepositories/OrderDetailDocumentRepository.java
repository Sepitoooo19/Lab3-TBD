package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.OrderDetailDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailDocumentRepository extends MongoRepository<OrderDetailDocument, String> {
    List<OrderDetailDocument> findByOrderId(String orderId);
}
