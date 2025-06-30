package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.CoverageAreaDocument;
import bdavanzadas.lab1.documentServices.CoverageAreaDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar operaciones relacionadas con documentos de áreas de cobertura.
 * Proporciona endpoints para realizar operaciones CRUD y consultas sobre áreas de cobertura.
 */
@RestController
@RequestMapping("/documents/coverage-area")
public class CoverageAreaDocumentController {

    /**
     * Servicio de documentos de áreas de cobertura.
     * Este servicio se utiliza para interactuar con la base de datos de áreas de cobertura.
     */
    private final CoverageAreaDocumentService coverageAreaService;

    /**
     * Constructor del controlador que inyecta el servicio de áreas de cobertura.
     * @param coverageAreaService Servicio de documentos de áreas de cobertura a utilizar.
     */
    @Autowired
    public CoverageAreaDocumentController(CoverageAreaDocumentService coverageAreaService) {
        this.coverageAreaService = coverageAreaService;
    }

    /**
     * Obtiene todos los documentos de áreas de cobertura.
     * @return Lista de documentos de áreas de cobertura con código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<CoverageAreaDocument>> getAll() {
        return ResponseEntity.ok(coverageAreaService.getAll());
    }

    /**
     * Obtiene un documento de área de cobertura por su ID.
     * @param id ID del documento de área de cobertura.
     * @return Documento de área de cobertura encontrado o NOT FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CoverageAreaDocument> getById(@PathVariable String id) {
        return coverageAreaService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Verifica si existe un documento de área de cobertura con el ID especificado.
     * @param coverageAreaId ID del área de cobertura a verificar.
     * @return true si existe un documento con el ID especificado, false en caso contrario.
     */
    @GetMapping("/exists/{coverageAreaId}")
    public ResponseEntity<Boolean> existsByCoverageAreaId(@PathVariable Integer coverageAreaId) {
        return ResponseEntity.ok(coverageAreaService.existsByCoverageAreaId(coverageAreaId));
    }

    /**
     * Crea un nuevo documento de área de cobertura.
     * @param document Documento de área de cobertura a crear.
     * @return Documento de área de cobertura creado con código 200 OK.
     */
    @PostMapping
    public ResponseEntity<CoverageAreaDocument> create(@RequestBody CoverageAreaDocument document) {
        return ResponseEntity.ok(coverageAreaService.save(document));
    }

    /**
     * Elimina un documento de área de cobertura por su ID.
     * @param id ID del documento de área de cobertura a eliminar.
     * @return Respuesta sin contenido con código 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        coverageAreaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}