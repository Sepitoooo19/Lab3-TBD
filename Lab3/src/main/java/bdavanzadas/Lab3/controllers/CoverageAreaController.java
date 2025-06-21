package bdavanzadas.Lab3.controllers;

import bdavanzadas.Lab3.entities.CoverageAreaEntity;
import bdavanzadas.Lab3.services.CoverageAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coverage-areas")
public class CoverageAreaController {

    private final CoverageAreaService coverageAreaService;

    @Autowired
    public CoverageAreaController(CoverageAreaService coverageAreaService) {
        this.coverageAreaService = coverageAreaService;
    }

    // Obtener todas las áreas
    @GetMapping
    public ResponseEntity<List<CoverageAreaEntity>> getAllCoverageAreas() {
        return ResponseEntity.ok(coverageAreaService.findAll());
    }

    // Obtener una por ID
    @GetMapping("/{id}")
    public ResponseEntity<CoverageAreaEntity> getCoverageAreaById(@PathVariable String id) {
        return coverageAreaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nueva área
    @PostMapping
    public ResponseEntity<CoverageAreaEntity> createCoverageArea(@RequestBody CoverageAreaEntity area) {
        return ResponseEntity.ok(coverageAreaService.save(area));
    }

    // Actualizar un área
    @PutMapping("/{id}")
    public ResponseEntity<CoverageAreaEntity> updateCoverageArea(
            @PathVariable String id,
            @RequestBody CoverageAreaEntity area) {
        return ResponseEntity.ok(coverageAreaService.update(id, area));
    }

    // Eliminar un área
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoverageArea(@PathVariable String id) {
        coverageAreaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
