package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.ClientDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientDocumentRepository extends MongoRepository<ClientDocument, String> {
    Optional<ClientDocument> findByUserId(String userId);

    // Método para buscar por RUT
    Optional<ClientDocument> findByRut(String rut);

    // Método para obtener solo el nombre por ID
    @Query(value = "{ '_id' : ?0 }", fields = "{ 'name' : 1 }")
    String findNameById(String clientId);


    boolean existsByClientId(Integer clientId);
    Optional<ClientDocument> findByClientId(Integer clientId);
}