package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.UserDocument;
import bdavanzadas.lab1.documentServices.UserDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents/user")
public class UserDocumentController {

    private final UserDocumentService userService;

    @Autowired
    public UserDocumentController(UserDocumentService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDocument>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDocument> getById(@PathVariable String id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDocument> getByUsername(@PathVariable String username) {
        return userService.getByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/exists/{userId}")
    public ResponseEntity<Boolean> existsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.existsByUserId(userId));
    }

    @GetMapping("/user-id/{userId}")
    public ResponseEntity<UserDocument> getByUserId(@PathVariable Integer userId) {
        return userService.getByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDocument> create(@RequestBody UserDocument user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
