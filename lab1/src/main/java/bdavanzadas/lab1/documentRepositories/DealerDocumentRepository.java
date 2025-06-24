package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.ClientDocument;
import bdavanzadas.lab1.documents.DealerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealerDocumentRepository extends MongoRepository<DealerDocument, String> {
    // Buscar repartidor por ID de usuario
    Optional<DealerDocument> findByUserId(String userId);

    // Buscar repartidor por RUT (único)
    Optional<DealerDocument> findByRut(String rut);

    // Obtener solo el nombre por ID
    @Query(value = "{ '_id' : ?0 }", fields = "{ 'name' : 1 }")
    String findNameById(String dealerId);

    boolean existsByDealerId(Integer clientId);
    Optional<DealerDocument> findByDealerId(Integer clientId);
}