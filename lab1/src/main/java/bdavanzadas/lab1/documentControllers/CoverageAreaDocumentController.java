package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.CoverageAreaDocument;
import bdavanzadas.lab1.documentServices.CoverageAreaDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents/coverage-area")
public class CoverageAreaDocumentController {

    private final CoverageAreaDocumentService coverageAreaService;

    @Autowired
    public CoverageAreaDocumentController(CoverageAreaDocumentService coverageAreaService) {
        this.coverageAreaService = coverageAreaService;
    }

    @GetMapping
    public ResponseEntity<List<CoverageAreaDocument>> getAll() {
        return ResponseEntity.ok(coverageAreaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoverageAreaDocument> getById(@PathVariable String id) {
        return coverageAreaService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/exists/{coverageAreaId}")
    public ResponseEntity<Boolean> existsByCoverageAreaId(@PathVariable Integer coverageAreaId) {
        return ResponseEntity.ok(coverageAreaService.existsByCoverageAreaId(coverageAreaId));
    }

    @PostMapping
    public ResponseEntity<CoverageAreaDocument> create(@RequestBody CoverageAreaDocument document) {
        return ResponseEntity.ok(coverageAreaService.save(document));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        coverageAreaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
