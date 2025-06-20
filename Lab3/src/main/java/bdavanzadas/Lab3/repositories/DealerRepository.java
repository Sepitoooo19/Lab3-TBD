package bdavanzadas.Lab3.repositories;

import bdavanzadas.Lab3.entities.DealerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface DealerRepository extends MongoRepository<DealerEntity, String> {
    Optional<DealerEntity> findByUserId(String userId);
}