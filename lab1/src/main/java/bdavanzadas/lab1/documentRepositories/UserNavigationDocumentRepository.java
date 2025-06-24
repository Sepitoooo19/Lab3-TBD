package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.UserNavigationDocument;
import bdavanzadas.lab1.projections.SearchWithoutOrderProjection;
import org.springframework.data.mongodb.repository.Aggregation;
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

    // Método básico usando query derivation
    List<UserNavigationDocument> findByEventTypeNotIn(List<String> excludedEventTypes);

}
