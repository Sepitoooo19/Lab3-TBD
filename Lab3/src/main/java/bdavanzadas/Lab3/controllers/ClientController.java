package bdavanzadas.Lab3.controllers;

import bdavanzadas.Lab3.entities.ClientEntity;
import bdavanzadas.Lab3.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "*")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientEntity> createClient(@RequestBody ClientEntity client) {
        return ResponseEntity.ok(clientService.saveClient(client));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClient(@PathVariable String id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ClientEntity> getClientByUserId(@PathVariable String userId) {
        return clientService.getClientByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ClientEntity> getAllClients() {
        return clientService.getAllClients();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/name")
    public ResponseEntity<String> getClientName(@PathVariable String id) {
        String name = clientService.getClientNameById(id);
        return name != null ?
                ResponseEntity.ok(name) :
                ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClientEntity> updateClient(
            @PathVariable String id,
            @RequestBody ClientEntity clientDetails) {
        try {
            return ResponseEntity.ok(clientService.updateClient(id, clientDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para actualización parcial
    @PatchMapping("/{id}")
    public ResponseEntity<ClientEntity> partialUpdateClient(
            @PathVariable String id,
            @RequestBody Map<String, Object> updates) {
        return clientService.getClientById(id)
                .map(client -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "name" -> client.setName((String) value);
                            case "email" -> client.setEmail((String) value);
                            case "phone" -> client.setPhone((String) value);
                            case "address" -> client.setAddress((String) value);
                            case "location" -> {
                                // Asume formato { "type": "Point", "coordinates": [long, lat] }
                                Map<?, ?> locMap = (Map<?, ?>) value;
                                List<Double> coords = (List<Double>) locMap.get("coordinates");
                                client.setLocation(new GeoJsonPoint(coords.get(0), coords.get(1)));
                            }
                        }
                    });
                    return ResponseEntity.ok(clientService.saveClient(client));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}