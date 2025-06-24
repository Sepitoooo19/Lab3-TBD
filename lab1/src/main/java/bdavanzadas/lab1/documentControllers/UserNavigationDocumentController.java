package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.UserNavigationDocument;
import bdavanzadas.lab1.documentServices.UserNavigationDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/documents/user-navigation")
public class UserNavigationDocumentController {

    private final UserNavigationDocumentService service;

    @Autowired
    public UserNavigationDocumentController(UserNavigationDocumentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UserNavigationDocument>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/user/{clientId}")
    public ResponseEntity<List<UserNavigationDocument>> getByClientId(@PathVariable Integer clientId) {
        return ResponseEntity.ok(service.getByClientId(clientId));
    }

    @PostMapping
    public ResponseEntity<UserNavigationDocument> create(@RequestBody UserNavigationDocument doc) {
        return ResponseEntity.ok(service.save(doc));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/non-order-actions")
    public ResponseEntity<List<UserNavigationDocument>> getNonOrderActions() {
        return ResponseEntity.ok(service.getNonOrderNavigationDocuments());
    }
}
