package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.EmergencyReportDocument;
import bdavanzadas.lab1.documentRepositories.EmergencyReportDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmergencyReportDocumentService {

    private final EmergencyReportDocumentRepository emergencyRepository;

    @Autowired
    public EmergencyReportDocumentService(EmergencyReportDocumentRepository emergencyRepository) {
        this.emergencyRepository = emergencyRepository;
    }

    public List<EmergencyReportDocument> getByOrderId(String orderId) {
        return emergencyRepository.findByOrderId(orderId);
    }

    public List<EmergencyReportDocument> getByDealerId(String dealerId) {
        return emergencyRepository.findByDealerId(dealerId);
    }

    public boolean existsByReportId(Integer reportId) {
        return emergencyRepository.existsByReportId(reportId);
    }

    public Optional<EmergencyReportDocument> getByReportId(Integer reportId) {
        return emergencyRepository.findByReportId(reportId);
    }

    public Optional<EmergencyReportDocument> getById(String id) {
        return emergencyRepository.findById(id);
    }

    public EmergencyReportDocument save(EmergencyReportDocument report) {
        return emergencyRepository.save(report);
    }

    public void deleteById(String id) {
        emergencyRepository.deleteById(id);
    }

    public List<EmergencyReportDocument> getAll() {
        return emergencyRepository.findAll();
    }
}
