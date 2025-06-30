package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.CustomerReviewDocument;
import bdavanzadas.lab1.documentServices.CustomerReviewDocumentService;
import bdavanzadas.lab1.projections.ReviewHourStatsProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bdavanzadas.lab1.projections.AverageRatingWithNameProjection;
import bdavanzadas.lab1.dtos.CompanyReviewDTO;
import bdavanzadas.lab1.documentServices.CompanyDocumentService;
import bdavanzadas.lab1.services.UserService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Controlador REST para manejar operaciones relacionadas con reseñas de clientes.
 * Proporciona endpoints para gestionar las valoraciones y comentarios que los clientes hacen sobre las empresas.
 */
@RestController
@RequestMapping("/documents/customer-review")
public class CustomerReviewDocumentController {

    /**
     * Servicio para operaciones con reseñas de clientes.
     */
    private final CustomerReviewDocumentService service;

    /**
     * Servicio para operaciones con documentos de empresas.
     */
    private final CompanyDocumentService companyService;

    /**
     * Servicio para operaciones con usuarios.
     */
    private final UserService userService;

    /**
     * Constructor que inyecta los servicios necesarios.
     * @param service Servicio de reseñas de clientes.
     * @param companyService Servicio de empresas.
     * @param userService Servicio de usuarios.
     */
    @Autowired
    public CustomerReviewDocumentController(CustomerReviewDocumentService service,
                                            CompanyDocumentService companyService,
                                            UserService userService) {
        this.service = service;
        this.companyService = companyService;
        this.userService = userService;
    }

    /**
     * Obtiene todas las reseñas de clientes.
     * @return Lista de todas las reseñas con código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<CustomerReviewDocument>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Obtiene las reseñas de un cliente específico.
     * @param clientId ID del cliente.
     * @return Lista de reseñas del cliente con código 200 OK.
     */
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CustomerReviewDocument>> getByClient(@PathVariable Integer clientId) {
        return ResponseEntity.ok(service.getByClientId(clientId));
    }

    /**
     * Obtiene las reseñas de una empresa específica.
     * @param companyId ID de la empresa.
     * @return Lista de reseñas de la empresa con código 200 OK.
     */
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<CustomerReviewDocument>> getByCompany(@PathVariable Integer companyId) {
        return ResponseEntity.ok(service.getByCompanyId(companyId));
    }

    /**
     * Busca una reseña por su ID único.
     * @param reviewId ID de la reseña.
     * @return Reseña encontrada con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/review-id/{reviewId}")
    public ResponseEntity<CustomerReviewDocument> getByReviewId(@PathVariable Integer reviewId) {
        return service.getByReviewId(reviewId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Verifica si existe una reseña con el ID especificado.
     * @param reviewId ID de la reseña a verificar.
     * @return true si existe la reseña, false en caso contrario.
     */
    @GetMapping("/exists/{reviewId}")
    public ResponseEntity<Boolean> existsByReviewId(@PathVariable Integer reviewId) {
        return ResponseEntity.ok(service.existsByReviewId(reviewId));
    }

    /**
     * Elimina una reseña por su ID.
     * @param id ID de la reseña a eliminar.
     * @return Respuesta sin contenido con código 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene el promedio de calificaciones con los nombres de las empresas.
     * @return Lista de promedios de calificación con nombres de empresas.
     */
    @GetMapping("/average-rating-with-name")
    public ResponseEntity<List<AverageRatingWithNameProjection>> getAverageRatingWithName() {
        return ResponseEntity.ok(service.getAverageRatingWithCompanyName());
    }

    /**
     * Busca reseñas que contengan las palabras clave especificadas.
     * @param keywords Lista de palabras clave para buscar.
     * @return Lista de reseñas que coinciden con las palabras clave.
     */
    @GetMapping("/search")
    public ResponseEntity<List<CustomerReviewDocument>> searchByKeywords(
            @RequestParam List<String> keywords) {
        String joined = String.join("|", keywords);
        return ResponseEntity.ok(service.findByKeywords(joined));
    }

    /**
     * Obtiene estadísticas de reseñas por hora del día.
     * @return Lista de estadísticas agrupadas por hora.
     */
    @GetMapping("/hourly-stats")
    public List<ReviewHourStatsProjection> getReviewStatsByHour() {
        return service.getReviewStatsByHour();
    }

    /**
     * Crea una nueva reseña para una empresa.
     * @param request DTO con los datos de la reseña.
     * @return Reseña creada con código 200 OK o códigos de error en caso de problemas.
     */
    @PostMapping
    public ResponseEntity<CustomerReviewDocument> createCompanyReview(
            @RequestBody CompanyReviewDTO request) {

        // Validar que la empresa exista
        if (!companyService.existsByCompanyId(request.getCompanyId())) {
            return ResponseEntity.badRequest().build();
        }

        // Obtener cliente autenticado
        Long clientId = userService.getAuthenticatedUserId();
        if (clientId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Crear y guardar la revisión con la zona horaria correcta
        CustomerReviewDocument review = new CustomerReviewDocument();
        review.setCompanyId(request.getCompanyId());
        review.setClientId(Integer.valueOf(clientId.intValue()));
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setDate(LocalDateTime.now(ZoneId.of("America/Santiago")));

        return ResponseEntity.ok(service.save(review));
    }
}