package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.EmergencyReportDocument;
import bdavanzadas.lab1.documentRepositories.EmergencyReportDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones con reportes de emergencias.
 * Proporciona métodos para buscar, crear, actualizar y eliminar
 * reportes de emergencias del sistema.
 */
@Service
public class EmergencyReportDocumentService {


    /**
     * Repositorio para acceder a los documentos de reportes de emergencias.
     * * Este repositorio permite realizar operaciones CRUD sobre los reportes
     * */
    private final EmergencyReportDocumentRepository emergencyRepository;

    /**
     * Constructor que inyecta el repositorio de reportes de emergencias.
     * @param emergencyRepository Repositorio de reportes de emergencias
     */
    @Autowired
    public EmergencyReportDocumentService(EmergencyReportDocumentRepository emergencyRepository) {
        this.emergencyRepository = emergencyRepository;
    }

    /**
     * Obtiene todos los reportes de emergencias registrados por id de orden
     * @param orderId ID de la orden asociada a los reportes
     * @return Lista de reportes de emergencias asociados a la orden
     */
    public List<EmergencyReportDocument> getByOrderId(String orderId) {
        return emergencyRepository.findByOrderId(orderId);
    }

    /**
     * Obtiene todos los reportes de emergencias registrados por id de repartidor
     * @param dealerId ID del repartidor asociado a los reportes
     * @return Lista de reportes de emergencias asociados al repartidor
     */
    public List<EmergencyReportDocument> getByDealerId(String dealerId) {
        return emergencyRepository.findByDealerId(dealerId);
    }

    /**
     * Verifica si existe un reporte de emergencia con el ID de reporte especificado.
     * @param reportId ID del reporte de emergencia
     * @return true si existe, false en caso contrario
     */
    public boolean existsByReportId(Integer reportId) {
        return emergencyRepository.existsByReportId(reportId);
    }

    /**
     * Obtiene un reporte de emergencia por su ID de reporte.
     * @param reportId ID del reporte de emergencia
     * @return Optional que contiene el reporte si existe
     */
    public Optional<EmergencyReportDocument> getByReportId(Integer reportId) {
        return emergencyRepository.findByReportId(reportId);
    }

    /**
     * Obtiene un reporte de emergencia por su ID de documento.
     * @param id ID del documento del reporte de emergencia
     * @return Optional que contiene el reporte si existe
     */
    public Optional<EmergencyReportDocument> getById(String id) {
        return emergencyRepository.findById(id);
    }

    /**
     * Guarda o actualiza un reporte de emergencia.
     * @param report Documento del reporte de emergencia a guardar
     * @return Reporte de emergencia guardado/actualizado
     */
    public EmergencyReportDocument save(EmergencyReportDocument report) {
        return emergencyRepository.save(report);
    }

    /**
     * Elimina un reporte de emergencia por su ID de documento.
     * @param id ID del documento del reporte de emergencia a eliminar
     * @throws org.springframework.dao.EmptyResultDataAccessException si no existe el reporte con el ID especificado
     */
    public void deleteById(String id) {
        emergencyRepository.deleteById(id);
    }

    /**
     * Obtiene todos los reportes de emergencias registrados.
     * @return Lista de todos los reportes de emergencias
     */
    public List<EmergencyReportDocument> getAll() {
        return emergencyRepository.findAll();
    }
}
