package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.OrderDetailDocument;
import bdavanzadas.lab1.documents.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDocumentRepository extends MongoRepository<OrderDocument, String> {

    // Buscar orders por clientId
    List<OrderDocument> findByClientId(String clientId);

    // Buscar orders por dealerId
    List<OrderDocument> findByDealerId(String dealerId);


    // findById, findAll, save, deleteById ya vienen con MongoRepository

    boolean existsByOrderId(Integer orderId);
    Optional<OrderDetailDocument> findByOrderId(Integer OrderId);
}
