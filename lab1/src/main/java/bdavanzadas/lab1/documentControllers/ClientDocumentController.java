package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.ClientDocument;
import bdavanzadas.lab1.documentServices.ClientDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/documents/clients")
public class ClientDocumentController {

    private final ClientDocumentService clientService;

    @Autowired
    public ClientDocumentController(ClientDocumentService clientService) {
        this.clientService = clientService;
    }
    //getAllClients

    @GetMapping
    public ResponseEntity<Iterable<ClientDocument>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<ClientDocument> getByUserId(@PathVariable String userId) {
        return clientService.getClientByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<ClientDocument> getByRut(@PathVariable String rut) {
        return clientService.getClientByRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/name")
    public ResponseEntity<String> getNameById(@PathVariable String id) {
        String name = clientService.getClientNameById(id);
        return name != null ? ResponseEntity.ok(name) : ResponseEntity.notFound().build();
    }

    @GetMapping("/exists/{clientId}")
    public ResponseEntity<Boolean> existsByClientId(@PathVariable Integer clientId) {
        return ResponseEntity.ok(clientService.existsByClientId(clientId));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDocument> getByClientId(@PathVariable Integer clientId) {
        return clientService.getClientByClientId(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClientDocument> createClient(@RequestBody ClientDocument clientDocument) {
        return ResponseEntity.ok(clientService.saveClient(clientDocument));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}