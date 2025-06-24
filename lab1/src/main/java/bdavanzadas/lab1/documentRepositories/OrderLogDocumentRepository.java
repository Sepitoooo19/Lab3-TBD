package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.OrderLogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderLogDocumentRepository extends MongoRepository<OrderLogDocument, String> {
    List<OrderLogDocument> findByOrderId(Integer orderId);
}
