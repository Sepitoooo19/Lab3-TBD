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

@RestController
@RequestMapping("/documents/customer-review")
public class CustomerReviewDocumentController {

    private final CustomerReviewDocumentService service;
    private final CompanyDocumentService companyService;
    private final UserService userService;

    @Autowired
    public CustomerReviewDocumentController(CustomerReviewDocumentService service,
                                            CompanyDocumentService companyService,
                                            UserService userService) {
        this.service = service;
        this.companyService = companyService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerReviewDocument>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CustomerReviewDocument>> getByClient(@PathVariable Integer clientId) {
        return ResponseEntity.ok(service.getByClientId(clientId));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<CustomerReviewDocument>> getByCompany(@PathVariable Integer companyId) {
        return ResponseEntity.ok(service.getByCompanyId(companyId));
    }

    @GetMapping("/review-id/{reviewId}")
    public ResponseEntity<CustomerReviewDocument> getByReviewId(@PathVariable Integer reviewId) {
        return service.getByReviewId(reviewId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/exists/{reviewId}")
    public ResponseEntity<Boolean> existsByReviewId(@PathVariable Integer reviewId) {
        return ResponseEntity.ok(service.existsByReviewId(reviewId));
    }

    /**
    @PostMapping
    public ResponseEntity<CustomerReviewDocument> create(@RequestBody CustomerReviewDocument doc) {
        return ResponseEntity.ok(service.save(doc));
    }
    **/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/average-rating-with-name")
    public ResponseEntity<List<AverageRatingWithNameProjection>> getAverageRatingWithName() {
        return ResponseEntity.ok(service.getAverageRatingWithCompanyName());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerReviewDocument>> searchByKeywords(
            @RequestParam List<String> keywords) {
        String joined = String.join("|", keywords); // e.g., "demora|error"
        return ResponseEntity.ok(service.findByKeywords(joined));
    }

    @GetMapping("/hourly-stats")
    public List<ReviewHourStatsProjection> getReviewStatsByHour() {
        return service.getReviewStatsByHour();
    }

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
