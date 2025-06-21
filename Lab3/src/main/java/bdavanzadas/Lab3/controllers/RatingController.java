package bdavanzadas.Lab3.controllers;

import bdavanzadas.Lab3.entities.RatingEntity;
import bdavanzadas.Lab3.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public ResponseEntity<List<RatingEntity>> getAll() {
        return ResponseEntity.ok(ratingService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingEntity> getById(@PathVariable String id) {
        return ratingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<RatingEntity>> getByClientId(@PathVariable String clientId) {
        return ResponseEntity.ok(ratingService.findByClientId(clientId));
    }

    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<RatingEntity>> getByDealerId(@PathVariable String dealerId) {
        return ResponseEntity.ok(ratingService.findByDealerId(dealerId));
    }

    @PostMapping
    public ResponseEntity<RatingEntity> create(@RequestBody RatingEntity rating) {
        return ResponseEntity.ok(ratingService.save(rating));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingEntity> update(@PathVariable String id, @RequestBody RatingEntity rating) {
        return ResponseEntity.ok(ratingService.update(id, rating));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        ratingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
