package bdavanzadas.Lab3.repositories;

import bdavanzadas.Lab3.entities.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    // Buscar orders por clientId
    List<OrderEntity> findByClientId(String clientId);

    // Buscar orders por dealerId
    List<OrderEntity> findByDealerId(String dealerId);

    // findById, findAll, save, deleteById ya vienen con MongoRepository
}
