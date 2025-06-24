package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.DealerDocument;
import bdavanzadas.lab1.documentServices.DealerDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents/dealer")
public class DealerDocumentController {

    private final DealerDocumentService dealerService;

    @Autowired
    public DealerDocumentController(DealerDocumentService dealerService) {
        this.dealerService = dealerService;
    }

    @GetMapping
    public ResponseEntity<Iterable<DealerDocument>> getAll() {
        return ResponseEntity.ok(dealerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealerDocument> getById(@PathVariable String id) {
        return dealerService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<DealerDocument> getByUserId(@PathVariable String userId) {
        return dealerService.getByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<DealerDocument> getByRut(@PathVariable String rut) {
        return dealerService.getByRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/name")
    public ResponseEntity<String> getNameById(@PathVariable String id) {
        String name = dealerService.getNameById(id);
        return name != null ? ResponseEntity.ok(name) : ResponseEntity.notFound().build();
    }

    @GetMapping("/exists/{dealerId}")
    public ResponseEntity<Boolean> existsByDealerId(@PathVariable Integer dealerId) {
        return ResponseEntity.ok(dealerService.existsByDealerId(dealerId));
    }

    @GetMapping("/dealer-id/{dealerId}")
    public ResponseEntity<DealerDocument> getByDealerId(@PathVariable Integer dealerId) {
        return dealerService.getByDealerId(dealerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DealerDocument> create(@RequestBody DealerDocument dealer) {
        return ResponseEntity.ok(dealerService.save(dealer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        dealerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
