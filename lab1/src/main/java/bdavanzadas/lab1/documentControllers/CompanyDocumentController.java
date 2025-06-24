package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.CompanyDocument;
import bdavanzadas.lab1.documentServices.CompanyDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents/companies")
public class CompanyDocumentController {

    private final CompanyDocumentService companyService;

    @Autowired
    public CompanyDocumentController(CompanyDocumentService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDocument>> getAll() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDocument> getById(@PathVariable String id) {
        return companyService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<CompanyDocument> getByRut(@PathVariable String rut) {
        return companyService.getByRut(rut)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<CompanyDocument>> getByType(@PathVariable String type) {
        return ResponseEntity.ok(companyService.getByType(type));
    }

    @GetMapping("/payment-method/{paymentMethodId}")
    public ResponseEntity<List<CompanyDocument>> getByPaymentMethod(@PathVariable String paymentMethodId) {
        return ResponseEntity.ok(companyService.getByPaymentMethodId(paymentMethodId));
    }

    @GetMapping("/coverage-area/{coverageAreaId}")
    public ResponseEntity<List<CompanyDocument>> getByCoverageArea(@PathVariable String coverageAreaId) {
        return ResponseEntity.ok(companyService.getByCoverageAreaId(coverageAreaId));
    }

    @GetMapping("/exists/{companyId}")
    public ResponseEntity<Boolean> existsByCompanyId(@PathVariable Integer companyId) {
        return ResponseEntity.ok(companyService.existsByCompanyId(companyId));
    }

    @PostMapping
    public ResponseEntity<CompanyDocument> create(@RequestBody CompanyDocument company) {
        return ResponseEntity.ok(companyService.save(company));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
