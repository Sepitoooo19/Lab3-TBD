package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.ClientDocument;
import bdavanzadas.lab1.documentServices.ClientDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/**
 * Controlador REST para manejar operaciones relacionadas con documentos de clientes.
 */
@RestController
@RequestMapping("/documents/clients")
public class ClientDocumentController {

    /**
     * Servicio de documentos de clientes.
     * Este servicio se utiliza para interactuar con la base de datos de documentos de clientes.
     */
    private final ClientDocumentService clientService;

    /**
     * Constructor del controlador que inyecta el servicio de documentos de clientes.
     * @param clientService Servicio de documentos de clientes a utilizar.
     */
    @Autowired
    public ClientDocumentController(ClientDocumentService clientService) {
        this.clientService = clientService;
    }



    /**
     * Obtiene todos los documentos de clientes.
     * @return Lista de documentos de clientes y código 200 OK.
     */
    @GetMapping
    public ResponseEntity<Iterable<ClientDocument>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }
    /**
     * Obtiene un documento de cliente por su ID.
     * @param userId ID del documento de cliente.
     * @return Documento de cliente encontrado o NOT FOUND si no existe.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ClientDocument> getByUserId(@PathVariable String userId) {
        return clientService.getClientByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene un documento de cliente por su RUT.
     * @param rut RUT del documento de cliente.
     * @return Documento de cliente encontrado o NOT FOUND si no existe.
     */
    @GetMapping("/rut/{rut}")
    public ResponseEntity<ClientDocument> getByRut(@PathVariable String rut) {
        return clientService.getClientByRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene el nombre de un cliente por su ID.
     * @param id ID del cliente.
     * @return Nombre del cliente o NOT FOUND si no existe.
     */
    @GetMapping("/{id}/name")
    public ResponseEntity<String> getNameById(@PathVariable String id) {
        String name = clientService.getClientNameById(id);
        return name != null ? ResponseEntity.ok(name) : ResponseEntity.notFound().build();
    }


    /**
     * Verifica si un cliente existe por su ID de cliente.
     * @param clientId ID del cliente.
     * @return true si el cliente existe, false en caso contrario.
     */
    @GetMapping("/exists/{clientId}")
    public ResponseEntity<Boolean> existsByClientId(@PathVariable Integer clientId) {
        return ResponseEntity.ok(clientService.existsByClientId(clientId));
    }

    /**
     * Obtiene un documento de cliente por su ID de cliente.
     * @param clientId ID del cliente.
     * @return Documento de cliente encontrado o NOT FOUND si no existe.
     */
    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDocument> getByClientId(@PathVariable Integer clientId) {
        return clientService.getClientByClientId(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo documento de cliente.
     * @param clientDocument Documento de cliente a crear.
     * @return Documento de cliente creado y código 200 OK.
     */
    @PostMapping
    public ResponseEntity<ClientDocument> createClient(@RequestBody ClientDocument clientDocument) {
        return ResponseEntity.ok(clientService.saveClient(clientDocument));
    }

    /**
     * Elimina un documento de cliente existente.
     * @param id ID del documento de cliente a eliminar.
     * @return Respuesta HTTP sin contenido (204 No Content) si se elimina correctamente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}