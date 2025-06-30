package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.UserNavigationDocument;
import bdavanzadas.lab1.documentServices.UserNavigationDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Controlador REST para gestionar el historial de navegación de usuarios.
 * Proporciona endpoints para registrar y consultar las acciones de navegación
 * de los usuarios en la plataforma.
 */
@RestController
@RequestMapping("/documents/user-navigation")
public class UserNavigationDocumentController {

    /**
     * Servicio para operaciones con documentos de navegación de usuarios.
     */
    private final UserNavigationDocumentService service;

    /**
     * Constructor que inyecta el servicio de navegación de usuarios.
     * @param service Servicio para gestionar documentos de navegación.
     */
    @Autowired
    public UserNavigationDocumentController(UserNavigationDocumentService service) {
        this.service = service;
    }

    /**
     * Obtiene todos los registros de navegación de usuarios.
     * @return Lista completa de documentos de navegación con código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<UserNavigationDocument>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Obtiene los registros de navegación de un usuario específico.
     * @param clientId ID del cliente cuyos registros se desean consultar.
     * @return Lista de documentos de navegación del cliente con código 200 OK.
     */
    @GetMapping("/user/{clientId}")
    public ResponseEntity<List<UserNavigationDocument>> getByClientId(@PathVariable Integer clientId) {
        return ResponseEntity.ok(service.getByClientId(clientId));
    }

    /**
     * Registra una nueva acción de navegación de usuario.
     * @param doc Documento con los datos de la navegación a registrar.
     * @return Documento de navegación creado con código 200 OK.
     */
    @PostMapping
    public ResponseEntity<UserNavigationDocument> create(@RequestBody UserNavigationDocument doc) {
        return ResponseEntity.ok(service.save(doc));
    }

    /**
     * Elimina un registro de navegación por su ID.
     * @param id ID del documento a eliminar.
     * @return Respuesta sin contenido con código 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene las acciones de navegación no relacionadas con pedidos.
     * @return Lista de documentos de navegación sin relación con pedidos con código 200 OK.
     */
    @GetMapping("/non-order-actions")
    public ResponseEntity<List<UserNavigationDocument>> getNonOrderActions() {
        return ResponseEntity.ok(service.getNonOrderNavigationDocuments());
    }
}