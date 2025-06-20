package bdavanzadas.Lab3.controllers;

import bdavanzadas.Lab3.entities.DealerEntity;
import bdavanzadas.Lab3.services.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dealers")
@CrossOrigin(origins = "*")
public class DealerController {

    private final DealerService dealerService;

    @Autowired
    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    // Crear nuevo repartidor
    @PostMapping
    public ResponseEntity<DealerEntity> createDealer(@RequestBody DealerEntity dealer) {
        return ResponseEntity.ok(dealerService.createDealer(dealer));
    }

    // Obtener repartidor por ID
    @GetMapping("/{id}")
    public ResponseEntity<DealerEntity> getDealerById(@PathVariable String id) {
        return dealerService.getDealerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Obtener repartidor por ID de usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<DealerEntity> getDealerByUserId(@PathVariable String userId) {
        return dealerService.getDealerByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar todos los repartidores
    @GetMapping
    public List<DealerEntity> getAllDealers() {
        return dealerService.getAllDealers();
    }

    // Actualizar repartidor completo
    @PutMapping("/{id}")
    public ResponseEntity<DealerEntity> updateDealer(
            @PathVariable String id,
            @RequestBody DealerEntity dealerDetails) {
        try {
            return ResponseEntity.ok(dealerService.updateDealer(id, dealerDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualización parcial (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<DealerEntity> partialUpdateDealer(
            @PathVariable String id,
            @RequestBody Map<String, Object> updates) {
        return dealerService.getDealerById(id)
                .map(existingDealer -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "name" -> existingDealer.setName((String) value);
                            case "email" -> existingDealer.setEmail((String) value);
                            case "phone" -> existingDealer.setPhone((String) value);
                            case "vehicle" -> existingDealer.setVehicle((String) value);
                            case "plate" -> existingDealer.setPlate((String) value);
                            case "location" -> {
                                Map<?, ?> locMap = (Map<?, ?>) value;
                                List<Double> coords = (List<Double>) locMap.get("coordinates");
                                existingDealer.setLocation(new GeoJsonPoint(coords.get(0), coords.get(1)));
                            }
                        }
                    });
                    return ResponseEntity.ok(dealerService.updateDealer(id, existingDealer));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar repartidor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealer(@PathVariable String id) {
        dealerService.deleteDealer(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener nombre del repartidor
    @GetMapping("/{id}/name")
    public ResponseEntity<String> getDealerName(@PathVariable String id) {
        String name = dealerService.getDealerNameById(id);
        return ResponseEntity.ok(name);
    }
}