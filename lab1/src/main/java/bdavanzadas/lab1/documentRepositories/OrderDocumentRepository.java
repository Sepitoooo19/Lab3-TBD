package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.OrderDetailDocument;
import bdavanzadas.lab1.documents.OrderDocument;
import bdavanzadas.lab1.dtos.RouteCountDTO;
import org.bson.Document;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderDocumentRepository extends MongoRepository<OrderDocument, String> {

    // Buscar orders por clientId
    List<OrderDocument> findByClientId(String clientId);

    // Buscar orders por dealerId
    List<OrderDocument> findByDealerId(Integer dealerId);


    // findById, findAll, save, deleteById ya vienen con MongoRepository

    boolean existsByOrderId(Integer orderId);
    Optional<OrderDocument> findByOrderId(Integer OrderId);
    
    @Aggregation(pipeline = {
        "{'$match': { 'dealerId': ?0, 'orderDate': { '$gte': ?1 } }}",
        "{'$group': { '_id': '$estimatedRoute', 'count': { '$sum': 1 } }}",
        "{'$sort': { 'count': -1 }}",
        "{'$limit': 1}"
    })
    RouteCountDTO findMostFrequentRouteByDealerId(Integer dealerId, LocalDateTime fromDate);
    
    // Buscar órdenes por dealerId y con fecha posterior a la especificada
    List<OrderDocument> findByDealerIdAndOrderDateAfter(Integer dealerId, LocalDateTime date);
}
