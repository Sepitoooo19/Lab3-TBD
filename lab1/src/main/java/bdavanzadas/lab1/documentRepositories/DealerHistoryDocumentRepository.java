package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.DealerHistoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DealerHistoryDocumentRepository extends MongoRepository<DealerHistoryDocument, String> {
    List<DealerHistoryDocument> findByDealerId(Integer dealerId);
}
