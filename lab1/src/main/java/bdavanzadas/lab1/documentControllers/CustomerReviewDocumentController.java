package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.CustomerReviewDocument;
import bdavanzadas.lab1.documentServices.CustomerReviewDocumentService;
import bdavanzadas.lab1.projections.ReviewHourStatsProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bdavanzadas.lab1.projections.AverageRatingWithNameProjection;


import java.util.List;

@RestController
@RequestMapping("/documents/customer-review")
public class CustomerReviewDocumentController {

    private final CustomerReviewDocumentService service;

    @Autowired
    public CustomerReviewDocumentController(CustomerReviewDocumentService service) {
        this.service = service;
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

    @PostMapping
    public ResponseEntity<CustomerReviewDocument> create(@RequestBody CustomerReviewDocument doc) {
        return ResponseEntity.ok(service.save(doc));
    }

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
}
