package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.OrderLogDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderLogDocumentRepository extends MongoRepository<OrderLogDocument, String> {
    List<OrderLogDocument> findByOrderId(Integer orderId);

    //3.- Contar cuántos pedidos tienen más de 3 cambios de estado en menos de 10 minutos.
    List<OrderLogDocument> findAllByOrderIdOrderByTimestampAsc(Integer orderId);
}
