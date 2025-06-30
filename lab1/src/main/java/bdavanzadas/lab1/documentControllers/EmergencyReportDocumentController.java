package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.EmergencyReportDocument;
import bdavanzadas.lab1.documentServices.EmergencyReportDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar reportes de emergencia.
 * Proporciona endpoints para crear, consultar y administrar reportes de situaciones de emergencia
 * relacionadas con pedidos o distribuidores.
 */
@RestController
@RequestMapping("/documents/emergency-report")
public class EmergencyReportDocumentController {

    /**
     * Servicio para operaciones con reportes de emergencia.
     */
    private final EmergencyReportDocumentService emergencyService;

    /**
     * Constructor que inyecta el servicio de reportes de emergencia.
     * @param emergencyService Servicio para gestionar reportes de emergencia.
     */
    @Autowired
    public EmergencyReportDocumentController(EmergencyReportDocumentService emergencyService) {
        this.emergencyService = emergencyService;
    }

    /**
     * Obtiene todos los reportes de emergencia registrados.
     * @return Lista completa de reportes de emergencia con código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<EmergencyReportDocument>> getAll() {
        return ResponseEntity.ok(emergencyService.getAll());
    }

    /**
     * Obtiene un reporte de emergencia por su ID de documento.
     * @param id ID del documento del reporte.
     * @return Reporte de emergencia con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmergencyReportDocument> getById(@PathVariable String id) {
        return emergencyService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene reportes de emergencia asociados a un pedido específico.
     * @param orderId ID del pedido.
     * @return Lista de reportes asociados al pedido con código 200 OK.
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<EmergencyReportDocument>> getByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(emergencyService.getByOrderId(orderId));
    }

    /**
     * Obtiene reportes de emergencia asociados a un distribuidor específico.
     * @param dealerId ID del distribuidor.
     * @return Lista de reportes asociados al distribuidor con código 200 OK.
     */
    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<EmergencyReportDocument>> getByDealerId(@PathVariable String dealerId) {
        return ResponseEntity.ok(emergencyService.getByDealerId(dealerId));
    }

    /**
     * Verifica si existe un reporte de emergencia con el ID especificado.
     * @param reportId ID del reporte a verificar.
     * @return true si existe el reporte, false en caso contrario.
     */
    @GetMapping("/exists/{reportId}")
    public ResponseEntity<Boolean> existsByReportId(@PathVariable Integer reportId) {
        return ResponseEntity.ok(emergencyService.existsByReportId(reportId));
    }

    /**
     * Obtiene un reporte de emergencia por su ID de reporte.
     * @param reportId ID numérico del reporte.
     * @return Reporte de emergencia con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/report-id/{reportId}")
    public ResponseEntity<EmergencyReportDocument> getByReportId(@PathVariable Integer reportId) {
        return emergencyService.getByReportId(reportId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo reporte de emergencia.
     * @param report Documento del reporte a crear.
     * @return Reporte de emergencia creado con código 200 OK.
     */
    @PostMapping
    public ResponseEntity<EmergencyReportDocument> create(@RequestBody EmergencyReportDocument report) {
        return ResponseEntity.ok(emergencyService.save(report));
    }

    /**
     * Elimina un reporte de emergencia por su ID de documento.
     * @param id ID del documento a eliminar.
     * @return Respuesta sin contenido con código 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        emergencyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}