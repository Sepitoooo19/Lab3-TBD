package bdavanzadas.Lab3.controllers;

import bdavanzadas.Lab3.entities.EmergencyReportEntity;
import bdavanzadas.Lab3.services.EmergencyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency-reports")
public class EmergencyReportController {

    private final EmergencyReportService emergencyReportService;

    @Autowired
    public EmergencyReportController(EmergencyReportService emergencyReportService) {
        this.emergencyReportService = emergencyReportService;
    }

    @GetMapping
    public ResponseEntity<List<EmergencyReportEntity>> getAll() {
        return ResponseEntity.ok(emergencyReportService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmergencyReportEntity> getById(@PathVariable String id) {
        return emergencyReportService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<EmergencyReportEntity>> getByOrderId(@PathVariable String orderId) {
        return ResponseEntity.ok(emergencyReportService.findByOrderId(orderId));
    }

    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<EmergencyReportEntity>> getByDealerId(@PathVariable String dealerId) {
        return ResponseEntity.ok(emergencyReportService.findByDealerId(dealerId));
    }

    @PostMapping
    public ResponseEntity<EmergencyReportEntity> create(@RequestBody EmergencyReportEntity emergencyReport) {
        return ResponseEntity.ok(emergencyReportService.save(emergencyReport));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmergencyReportEntity> update(@PathVariable String id, @RequestBody EmergencyReportEntity emergencyReport) {
        return ResponseEntity.ok(emergencyReportService.update(id, emergencyReport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        emergencyReportService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
