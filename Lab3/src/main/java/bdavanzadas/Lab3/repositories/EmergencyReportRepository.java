package bdavanzadas.Lab3.repositories;

import bdavanzadas.Lab3.entities.EmergencyReportEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmergencyReportRepository extends MongoRepository<EmergencyReportEntity, String> {
    List<EmergencyReportEntity> findByOrderId(String orderId);
    List<EmergencyReportEntity> findByDealerId(String dealerId);
}
