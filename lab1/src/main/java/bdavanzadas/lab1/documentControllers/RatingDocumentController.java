package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.RatingDocument;
import bdavanzadas.lab1.documentServices.RatingDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar calificaciones de clientes.
 * Proporciona endpoints para operaciones CRUD sobre las calificaciones
 * que los clientes asignan a distribuidores o servicios.
 */
@RestController
@RequestMapping("/documents/rating")
public class RatingDocumentController {

    /**
     * Servicio para operaciones con calificaciones.
     */
    private final RatingDocumentService ratingService;

    /**
     * Constructor que inyecta el servicio de calificaciones.
     * @param ratingService Servicio para gestionar calificaciones.
     */
    @Autowired
    public RatingDocumentController(RatingDocumentService ratingService) {
        this.ratingService = ratingService;
    }

    /**
     * Obtiene todas las calificaciones registradas en el sistema.
     * @return Lista completa de calificaciones con código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<RatingDocument>> getAll() {
        return ResponseEntity.ok(ratingService.getAll());
    }

    /**
     * Obtiene una calificación por su ID de documento.
     * @param id ID del documento de calificación.
     * @return Calificación con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RatingDocument> getById(@PathVariable String id) {
        return ratingService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene las calificaciones realizadas por un cliente específico.
     * @param clientId ID del cliente.
     * @return Lista de calificaciones del cliente con código 200 OK.
     */
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<RatingDocument>> getByClientId(@PathVariable String clientId) {
        return ResponseEntity.ok(ratingService.getByClientId(clientId));
    }

    /**
     * Obtiene las calificaciones recibidas por un distribuidor específico.
     * @param dealerId ID del distribuidor.
     * @return Lista de calificaciones del distribuidor con código 200 OK.
     */
    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<RatingDocument>> getByDealerId(@PathVariable String dealerId) {
        return ResponseEntity.ok(ratingService.getByDealerId(dealerId));
    }

    /**
     * Verifica si existe una calificación con el ID especificado.
     * @param ratingId ID de la calificación a verificar.
     * @return true si existe la calificación, false en caso contrario.
     */
    @GetMapping("/exists/{ratingId}")
    public ResponseEntity<Boolean> existsByRatingId(@PathVariable Integer ratingId) {
        return ResponseEntity.ok(ratingService.existsByRatingId(ratingId));
    }

    /**
     * Obtiene una calificación por su ID numérico.
     * @param ratingId ID numérico de la calificación.
     * @return Calificación con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/rating-id/{ratingId}")
    public ResponseEntity<RatingDocument> getByRatingId(@PathVariable Integer ratingId) {
        return ratingService.getByRatingId(ratingId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea una nueva calificación.
     * @param rating Documento de calificación a crear.
     * @return Calificación creada con código 200 OK.
     */
    @PostMapping
    public ResponseEntity<RatingDocument> create(@RequestBody RatingDocument rating) {
        return ResponseEntity.ok(ratingService.save(rating));
    }

    /**
     * Elimina una calificación por su ID de documento.
     * @param id ID del documento a eliminar.
     * @return Respuesta sin contenido con código 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        ratingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}