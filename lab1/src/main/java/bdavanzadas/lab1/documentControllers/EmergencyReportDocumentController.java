package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.EmergencyReportDocument;
import bdavanzadas.lab1.documentServices.EmergencyReportDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents/emergency-report")
public class EmergencyReportDocumentController {

    private final EmergencyReportDocumentService emergencyService;

    @Autowired
    public EmergencyReportDocumentController(EmergencyReportDocumentService emergencyService) {
        this.emergencyService = emergencyService;
    }

    @GetMapping
    public ResponseEntity<List<EmergencyReportDocument>> getAll() {
        return ResponseEntity.ok(emergencyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmergencyReportDocument> getById(@PathVariable String id) {
        return emergencyService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<EmergencyReportDocument>> getByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(emergencyService.getByOrderId(orderId));
    }

    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<EmergencyReportDocument>> getByDealerId(@PathVariable String dealerId) {
        return ResponseEntity.ok(emergencyService.getByDealerId(dealerId));
    }

    @GetMapping("/exists/{reportId}")
    public ResponseEntity<Boolean> existsByReportId(@PathVariable Integer reportId) {
        return ResponseEntity.ok(emergencyService.existsByReportId(reportId));
    }

    @GetMapping("/report-id/{reportId}")
    public ResponseEntity<EmergencyReportDocument> getByReportId(@PathVariable Integer reportId) {
        return emergencyService.getByReportId(reportId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmergencyReportDocument> create(@RequestBody EmergencyReportDocument report) {
        return ResponseEntity.ok(emergencyService.save(report));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        emergencyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
