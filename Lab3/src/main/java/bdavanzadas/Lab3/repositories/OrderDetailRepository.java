package bdavanzadas.Lab3.repositories;

import bdavanzadas.Lab3.entities.OrderDetailEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends MongoRepository<OrderDetailEntity, String> {
    List<OrderDetailEntity> findByOrderId(String orderId);
}