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

/**
 * Controlador REST para gestionar el historial de actividades de los distribuidores.
 * Proporciona endpoints para acceder al historial de ubicaciones y actividades de los repartidores.
 */
@RestController
@RequestMapping("/documents/dealer-history")
public class DealerHistoryDocumentController {

    /**
     * Servicio para operaciones con el historial de distribuidores.
     */
    private final DealerHistoryDocumentService service;

    /**
     * Constructor que inyecta el servicio de historial de distribuidores.
     * @param service Servicio de documentos de historial de distribuidores.
     */
    @Autowired
    public DealerHistoryDocumentController(DealerHistoryDocumentService service) {
        this.service = service;
    }

    /**
     * Obtiene todos los registros del historial de distribuidores.
     * @return Lista completa de registros históricos con código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<DealerHistoryDocument>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    /**
     * Obtiene el historial de un distribuidor específico.
     * @param dealerId ID del distribuidor.
     * @return Lista de registros históricos del distribuidor con código 200 OK.
     */
    @GetMapping("/dealer/{dealerId}")
    public ResponseEntity<List<DealerHistoryDocument>> getByDealerId(@PathVariable Integer dealerId) {
        return ResponseEntity.ok(service.getByDealerId(dealerId));
    }

    /**
     * Crea un nuevo registro en el historial de distribuidores.
     * @param history Documento de historial a crear.
     * @return Documento de historial creado con código 200 OK.
     */
    @PostMapping
    public ResponseEntity<DealerHistoryDocument> create(@RequestBody DealerHistoryDocument history) {
        return ResponseEntity.ok(service.save(history));
    }

    /**
     * Elimina un registro del historial por su ID.
     * @param id ID del documento a eliminar.
     * @return Respuesta sin contenido con código 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene las ubicaciones frecuentes de los distribuidores en los últimos 7 días.
     * @return Lista de DTOs con ubicaciones frecuentes o lista vacía si no hay datos.
     * @apiNote Maneja errores internos devolviendo código 500 INTERNAL SERVER ERROR.
     */
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
            return ResponseEntity.internalServerError().build();
        }
    }
}