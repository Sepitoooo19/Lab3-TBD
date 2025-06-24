package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.UserNavigationDocument;
import bdavanzadas.lab1.documentServices.UserNavigationDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserNavigationDocument>> getByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
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
}
