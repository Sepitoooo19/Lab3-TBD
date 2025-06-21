package bdavanzadas.Lab3.repositories;

import bdavanzadas.Lab3.entities.CoverageAreaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoverageAreaRepository extends MongoRepository<CoverageAreaEntity, String> {

    // No necesitas declarar nada si sólo usas CRUD básico.
    // MongoRepository ya incluye:
    // - findAll()
    // - findById(String id)
    // - save()
    // - deleteById()
}
