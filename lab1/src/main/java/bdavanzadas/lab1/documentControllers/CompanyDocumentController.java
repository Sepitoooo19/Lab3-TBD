package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.CompanyDocument;
import bdavanzadas.lab1.documentServices.CompanyDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para manejar operaciones relacionadas con documentos de compañías.
 * Proporciona endpoints para realizar operaciones CRUD y consultas específicas sobre documentos de compañías.
 */
@RestController
@RequestMapping("/documents/companies")
public class CompanyDocumentController {

    /**
     * Servicio de documentos de compañías.
     * Este servicio se utiliza para interactuar con la base de datos de documentos de compañías.
     */
    private final CompanyDocumentService companyService;

    /**
     * Constructor del controlador que inyecta el servicio de documentos de compañías.
     * @param companyService Servicio de documentos de compañías a utilizar.
     */
    @Autowired
    public CompanyDocumentController(CompanyDocumentService companyService) {
        this.companyService = companyService;
    }

    /**
     * Obtiene todos los documentos de compañías.
     * @return Lista de documentos de compañías y código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<CompanyDocument>> getAll() {
        return ResponseEntity.ok(companyService.getAll());
    }

    /**
     * Obtiene un documento de compañía por su ID.
     * @param id ID del documento de compañía.
     * @return Documento de compañía encontrado o NOT FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDocument> getById(@PathVariable String id) {
        return companyService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene un documento de compañía por su RUT.
     * @param rut RUT del documento de compañía.
     * @return Documento de compañía encontrado o NOT FOUND si no existe.
     */
    @GetMapping("/rut/{rut}")
    public ResponseEntity<CompanyDocument> getByRut(@PathVariable String rut) {
        return companyService.getByRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene documentos de compañía por su tipo.
     * @param type Tipo/categoría del documento de compañía.
     * @return Lista de documentos de compañía encontrados o lista vacía si no existen.
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<List<CompanyDocument>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(companyService.getByType(type));
    }

    /**
     * Obtiene documentos de compañía por su método de pago.
     * @param paymentMethodId ID del método de pago a buscar.
     * @return Lista de documentos de compañía que usan el método de pago especificado.
     */
    @GetMapping("/payment-method/{paymentMethodId}")
    public ResponseEntity<List<CompanyDocument>> getByPaymentMethod(@PathVariable String paymentMethodId) {
        return ResponseEntity.ok(companyService.getByPaymentMethodId(paymentMethodId));
    }

    /**
     * Obtiene documentos de compañía por su área de cobertura.
     * @param coverageAreaId ID del área de cobertura a buscar.
     * @return Lista de documentos de compañía que operan en el área de cobertura especificada.
     */
    @GetMapping("/coverage-area/{coverageAreaId}")
    public ResponseEntity<List<CompanyDocument>> getByCoverageArea(@PathVariable String coverageAreaId) {
        return ResponseEntity.ok(companyService.getByCoverageAreaId(coverageAreaId));
    }

    /**
     * Verifica si existe un documento de compañía con el ID especificado.
     * @param companyId ID de la compañía a verificar.
     * @return true si existe un documento con el ID especificado, false en caso contrario.
     */
    @GetMapping("/exists/{companyId}")
    public ResponseEntity<Boolean> existsByCompanyId(@PathVariable Integer companyId) {
        return ResponseEntity.ok(companyService.existsByCompanyId(companyId));
    }

    /**
     * Crea un nuevo documento de compañía.
     * @param company Documento de compañía a crear.
     * @return Documento de compañía creado con código 200 OK.
     */
    @PostMapping
    public ResponseEntity<CompanyDocument> create(@RequestBody CompanyDocument company) {
        return ResponseEntity.ok(companyService.save(company));
    }

    /**
     * Elimina un documento de compañía por su ID.
     * @param id ID del documento de compañía a eliminar.
     * @return Respuesta sin contenido con código 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}