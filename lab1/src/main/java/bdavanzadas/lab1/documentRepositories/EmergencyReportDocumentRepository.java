package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.EmergencyReportDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones con reportes de emergencia en MongoDB.
 * Proporciona métodos para consultar y gestionar reportes de emergencia
 * asociados a pedidos y repartidores.
 */
@Repository
public interface EmergencyReportDocumentRepository extends MongoRepository<EmergencyReportDocument, String> {

    /**
     * Busca reportes de emergencia asociados a un pedido específico.
     * @param orderId ID del pedido relacionado con los reportes.
     * @return Lista de reportes de emergencia asociados al pedido.
     */
    List<EmergencyReportDocument> findByOrderId(String orderId);

    /**
     * Busca reportes de emergencia asociados a un repartidor específico.
     * @param dealerId ID del repartidor relacionado con los reportes.
     * @return Lista de reportes de emergencia asociados al repartidor.
     */
    List<EmergencyReportDocument> findByDealerId(String dealerId);

    /**
     * Verifica si existe un reporte de emergencia con el ID especificado.
     * @param reportId ID del reporte de emergencia a verificar.
     * @return true si existe un reporte con ese ID, false en caso contrario.
     * @note El parámetro en la implementación se llama 'EmergencyReportId' pero debería ser 'reportId'
     * para mantener consistencia con otros métodos.
     */
    boolean existsByReportId(Integer reportId);

    /**
     * Busca un reporte de emergencia por su ID único.
     * @param reportId ID numérico del reporte de emergencia.
     * @return Optional que contiene el reporte si existe.
     */
    Optional<EmergencyReportDocument> findByReportId(Integer reportId);


}