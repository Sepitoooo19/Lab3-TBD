package bdavanzadas.Lab3.repositories;

import bdavanzadas.Lab3.entities.ClientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {
    Optional<ClientEntity> findByUserId(String userId);

    // Método para buscar por RUT
    Optional<ClientEntity> findByRut(String rut);

    // Método para obtener solo el nombre por ID
    @Query(value = "{ '_id' : ?0 }", fields = "{ 'name' : 1 }")
    String findNameById(String clientId);
}