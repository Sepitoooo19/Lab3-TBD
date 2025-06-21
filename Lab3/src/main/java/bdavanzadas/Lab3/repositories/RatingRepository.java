package bdavanzadas.Lab3.repositories;

import bdavanzadas.Lab3.entities.RatingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<RatingEntity, String> {

    // Buscar ratings por clientId
    List<RatingEntity> findByClientId(String clientId);

    // Buscar ratings por dealerId
    List<RatingEntity> findByDealerId(String dealerId);

}
