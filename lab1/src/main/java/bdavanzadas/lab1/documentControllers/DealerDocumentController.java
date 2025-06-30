package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.DealerDocument;
import bdavanzadas.lab1.documentServices.DealerDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para manejar operaciones relacionadas con documentos de distribuidores.
 * Proporciona endpoints para gestionar la información de los distribuidores del sistema.
 */
@RestController
@RequestMapping("/documents/dealer")
public class DealerDocumentController {

    /**
     * Servicio para operaciones con documentos de distribuidores.
     */
    private final DealerDocumentService dealerService;

    /**
     * Constructor que inyecta el servicio de distribuidores.
     * @param dealerService Servicio de documentos de distribuidores.
     */
    @Autowired
    public DealerDocumentController(DealerDocumentService dealerService) {
        this.dealerService = dealerService;
    }

    /**
     * Obtiene todos los documentos de distribuidores.
     * @return Lista iterable de todos los distribuidores con código 200 OK.
     */
    @GetMapping
    public ResponseEntity<Iterable<DealerDocument>> getAll() {
        return ResponseEntity.ok(dealerService.getAll());
    }

    /**
     * Obtiene un distribuidor por su ID de documento.
     * @param id ID del documento del distribuidor.
     * @return Documento del distribuidor con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DealerDocument> getById(@PathVariable String id) {
        return dealerService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene un distribuidor por su ID de usuario.
     * @param userId ID del usuario asociado al distribuidor.
     * @return Documento del distribuidor con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<DealerDocument> getByUserId(@PathVariable String userId) {
        return dealerService.getByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene un distribuidor por su RUT.
     * @param rut RUT del distribuidor.
     * @return Documento del distribuidor con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/rut/{rut}")
    public ResponseEntity<DealerDocument> getByRut(@PathVariable String rut) {
        return dealerService.getByRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene el nombre de un distribuidor por su ID de documento.
     * @param id ID del documento del distribuidor.
     * @return Nombre del distribuidor con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/{id}/name")
    public ResponseEntity<String> getNameById(@PathVariable String id) {
        String name = dealerService.getNameById(id);
        return name != null ? ResponseEntity.ok(name) : ResponseEntity.notFound().build();
    }

    /**
     * Verifica si existe un distribuidor con el ID especificado.
     * @param dealerId ID del distribuidor a verificar.
     * @return true si existe el distribuidor, false en caso contrario.
     */
    @GetMapping("/exists/{dealerId}")
    public ResponseEntity<Boolean> existsByDealerId(@PathVariable Integer dealerId) {
        return ResponseEntity.ok(dealerService.existsByDealerId(dealerId));
    }

    /**
     * Obtiene un distribuidor por su ID de distribuidor.
     * @param dealerId ID del distribuidor.
     * @return Documento del distribuidor con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/dealer-id/{dealerId}")
    public ResponseEntity<DealerDocument> getByDealerId(@PathVariable Integer dealerId) {
        return dealerService.getByDealerId(dealerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo documento de distribuidor.
     * @param dealer Documento del distribuidor a crear.
     * @return Documento del distribuidor creado con código 200 OK.
     */
    @PostMapping
    public ResponseEntity<DealerDocument> create(@RequestBody DealerDocument dealer) {
        return ResponseEntity.ok(dealerService.save(dealer));
    }

    /**
     * Elimina un distribuidor por su ID de documento.
     * @param id ID del documento del distribuidor a eliminar.
     * @return Respuesta sin contenido con código 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        dealerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}