package bdavanzadas.Lab3.services;

import bdavanzadas.Lab3.entities.EmergencyReportEntity;
import bdavanzadas.Lab3.repositories.EmergencyReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmergencyReportService {

    private final EmergencyReportRepository emergencyReportRepository;

    @Autowired
    public EmergencyReportService(EmergencyReportRepository emergencyReportRepository) {
        this.emergencyReportRepository = emergencyReportRepository;
    }

    public List<EmergencyReportEntity> findAll() {
        return emergencyReportRepository.findAll();
    }

    public Optional<EmergencyReportEntity> findById(String id) {
        return emergencyReportRepository.findById(id);
    }

    public List<EmergencyReportEntity> findByOrderId(String orderId) {
        return emergencyReportRepository.findByOrderId(orderId);
    }

    public List<EmergencyReportEntity> findByDealerId(String dealerId) {
        return emergencyReportRepository.findByDealerId(dealerId);
    }

    public EmergencyReportEntity save(EmergencyReportEntity emergencyReport) {
        return emergencyReportRepository.save(emergencyReport);
    }

    public EmergencyReportEntity update(String id, EmergencyReportEntity emergencyReport) {
        emergencyReport.setId(id);
        return emergencyReportRepository.save(emergencyReport);
    }

    public void delete(String id) {
        emergencyReportRepository.deleteById(id);
    }
}
