package bdavanzadas.Lab3.repositories;

import bdavanzadas.Lab3.entities.DealerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface DealerRepository extends MongoRepository<DealerEntity, String> {
    // Buscar repartidor por ID de usuario
    Optional<DealerEntity> findByUserId(String userId);

    // Buscar repartidor por RUT (único)
    Optional<DealerEntity> findByRut(String rut);

    // Obtener solo el nombre por ID
    @Query(value = "{ '_id' : ?0 }", fields = "{ 'name' : 1 }")
    String findNameById(String dealerId);
}