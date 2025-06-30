package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.UserDocument;
import bdavanzadas.lab1.documentServices.UserDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controlador REST para gestionar documentos de usuarios.
 * Proporciona endpoints para crear, consultar y administrar usuarios.
 */
@RestController
@RequestMapping("/documents/user")
public class UserDocumentController {

    /**
     * Servicio para operaciones con documentos de usuarios.
     */
    private final UserDocumentService userService;

    /**
     * Constructor que inyecta el servicio de documentos de usuarios.
     * @param userService Servicio para gestionar documentos de usuarios.
     */
    @Autowired
    public UserDocumentController(UserDocumentService userService) {
        this.userService = userService;
    }

    /**
     * Obtiene todos los documentos de usuarios.
     * @return Lista de documentos de usuarios con código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<UserDocument>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    /**
     * Obtiene un documento de usuario por su ID.
     * @param id ID del documento de usuario.
     * @return Documento de usuario encontrado o NOT FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDocument> getById(@PathVariable String id) {
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    /**
     *
     * Obtiene un documento de usuario por su nombre de usuario.
     * @param username Nombre de usuario.
     * */
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDocument> getByUsername(@PathVariable String username) {
        return userService.getByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Verifica si un usuario existe por su ID de usuario.
     * @param userId ID del usuario.
     * @return true si el usuario existe, false en caso contrario.
     */
    @GetMapping("/exists/{userId}")
    public ResponseEntity<Boolean> existsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.existsByUserId(userId));
    }

    /**
     * Obtiene un documento de usuario por su ID de usuario.
     * @param userId ID del usuario.
     * @return Documento de usuario encontrado o NOT FOUND si no existe.
     */
    @GetMapping("/user-id/{userId}")
    public ResponseEntity<UserDocument> getByUserId(@PathVariable Integer userId) {
        return userService.getByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo documento de usuario.
     * @param user Documento del usuario a crear.
     * @return Documento de usuario creado con código 200 OK.
     */
    @PostMapping
    public ResponseEntity<UserDocument> create(@RequestBody UserDocument user) {
        return ResponseEntity.ok(userService.save(user));
    }

    /**
     * Elimina un documento de usuario por su ID.
     * @param id ID del documento de usuario a eliminar.
     * @return Respuesta vacía con código 204 No Content si se elimina correctamente.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
