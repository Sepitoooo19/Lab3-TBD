package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.RatingDocument;
import bdavanzadas.lab1.documentServices.RatingDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents/rating")
public class RatingDocumentController {

    private final RatingDocumentService ratingService;

    @Autowired
    public RatingDocumentController(RatingDocumentService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public ResponseEntity<List<RatingDocument>> getAll() {
        return ResponseEntity.ok(ratingService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingDocument> getById(@PathVariable String id) {
        return ratingService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<RatingDocument>> getByClientId(@PathVariable String clientId) {
        return ResponseEntity.ok(ratingService.getByClientId(clientId));
    }

    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<RatingDocument>> getByDealerId(@PathVariable String dealerId) {
        return ResponseEntity.ok(ratingService.getByDealerId(dealerId));
    }

    @GetMapping("/exists/{ratingId}")
    public ResponseEntity<Boolean> existsByRatingId(@PathVariable Integer ratingId) {
        return ResponseEntity.ok(ratingService.existsByRatingId(ratingId));
    }

    @GetMapping("/rating-id/{ratingId}")
    public ResponseEntity<RatingDocument> getByRatingId(@PathVariable Integer ratingId) {
        return ratingService.getByRatingId(ratingId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RatingDocument> create(@RequestBody RatingDocument rating) {
        return ResponseEntity.ok(ratingService.save(rating));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        ratingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
