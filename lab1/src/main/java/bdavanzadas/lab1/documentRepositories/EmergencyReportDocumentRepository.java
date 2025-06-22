package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.ClientDocument;
import bdavanzadas.lab1.documents.EmergencyReportDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmergencyReportDocumentRepository extends MongoRepository<EmergencyReportDocument, String> {
    List<EmergencyReportDocument> findByOrderId(String orderId);
    List<EmergencyReportDocument> findByDealerId(String dealerId);

    boolean existsByReportId(Integer EmergencyReportId);
    Optional<EmergencyReportDocument> findByReportId(Integer reportId);
}
