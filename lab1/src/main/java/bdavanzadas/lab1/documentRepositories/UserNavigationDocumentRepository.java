package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.UserNavigationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface UserNavigationDocumentRepository extends MongoRepository<UserNavigationDocument, String> {
    List<UserNavigationDocument> findByClientId(Integer clientId);

    // Encontrar todas las búsquedas
    List<UserNavigationDocument> findByEventType(String eventType);

    // Encontrar por tipo de evento y cliente
    List<UserNavigationDocument> findByClientIdAndEventType(Integer clientId, String eventType);

    // Encontrar clientes distintos que realizaron cierto tipo de evento
    @Query(value = "{'eventType': ?0}", fields = "{'clientId': 1}")
    List<Integer> findDistinctClientIdsByEventType(String eventType);
}
