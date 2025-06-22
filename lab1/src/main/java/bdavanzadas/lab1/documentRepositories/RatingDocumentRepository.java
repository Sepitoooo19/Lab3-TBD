package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.RatingDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingDocumentRepository extends MongoRepository<RatingDocument, String> {

    // Buscar ratings por clientId
    List<RatingDocument> findByClientId(String clientId);

    // Buscar ratings por dealerId
    List<RatingDocument> findByDealerId(String dealerId);

}
