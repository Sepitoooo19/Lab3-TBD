package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.DealerHistoryDocument;
import bdavanzadas.lab1.documentServices.DealerHistoryDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents/dealer-history")
public class DealerHistoryDocumentController {

    private final DealerHistoryDocumentService service;

    @Autowired
    public DealerHistoryDocumentController(DealerHistoryDocumentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DealerHistoryDocument>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<DealerHistoryDocument>> getByDealerId(@PathVariable Integer dealerId) {
        return ResponseEntity.ok(service.getByDealerId(dealerId));
    }

    @PostMapping
    public ResponseEntity<DealerHistoryDocument> create(@RequestBody DealerHistoryDocument history) {
        return ResponseEntity.ok(service.save(history));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
