package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.DealerHistoryDocument;
import bdavanzadas.lab1.documentServices.DealerHistoryDocumentService;
import bdavanzadas.lab1.dtos.DealerFrequentLocationDTO;
import bdavanzadas.lab1.projections.DealerFrequentLocationProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/documents/dealer-history")
public class DealerHistoryDocumentController {

    private final DealerHistoryDocumentService service;

    @Autowired
    public DealerHistoryDocumentController(DealerHistoryDocumentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DealerHistoryDocument>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<DealerHistoryDocument>> getByDealerId(@PathVariable Integer dealerId) {
        return ResponseEntity.ok(service.getByDealerId(dealerId));
    }

    @PostMapping
    public ResponseEntity<DealerHistoryDocument> create(@RequestBody DealerHistoryDocument history) {
        return ResponseEntity.ok(service.save(history));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/frequent-locations")
    public ResponseEntity<List<DealerFrequentLocationDTO>> getFrequentLocations() {
        try {
            List<DealerFrequentLocationProjection> projections = service.getFrequentLocationsLast7Days();
            
            if (projections == null || projections.isEmpty()) {
                System.out.println("No se encontraron ubicaciones frecuentes");
                return ResponseEntity.ok(Collections.emptyList());
            }
            
            List<DealerFrequentLocationDTO> result = projections.stream()
                .map(projection -> {
                    try {
                        List<Double> location = projection.getLocation();
                        if (location == null || location.size() < 2) {
                            System.err.println("Ubicación inválida para el repartidor: " + projection.getDealerId());
                            return null;
                        }
                        return new DealerFrequentLocationDTO(
                            projection.getDealerId(),
                            location,
                            projection.getCount() != null ? projection.getCount() : 1L
                        );
                    } catch (Exception e) {
                        System.err.println("Error procesando proyección: " + e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
            
            System.out.println("Devolviendo " + result.size() + " ubicaciones frecuentes");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.err.println("Error al obtener ubicaciones frecuentes: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

}
