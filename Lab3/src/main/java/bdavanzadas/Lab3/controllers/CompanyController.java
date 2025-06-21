package bdavanzadas.Lab3.controllers;

import bdavanzadas.Lab3.entities.CompanyEntity;
import bdavanzadas.Lab3.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // Obtener todas las compañías
    @GetMapping
    public ResponseEntity<List<CompanyEntity>> getAllCompanies() {
        List<CompanyEntity> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    // Obtener una compañía por ID
    @GetMapping("/{id}")
    public ResponseEntity<CompanyEntity> getCompanyById(@PathVariable String id) {
        return companyService.getCompanyById(id)
                .map(company -> new ResponseEntity<>(company, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear una nueva compañía
    @PostMapping
    public ResponseEntity<CompanyEntity> createCompany(@RequestBody CompanyEntity company) {
        CompanyEntity savedCompany = companyService.saveCompany(company);
        return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
    }

    // Actualizar una compañía existente
    @PutMapping("/{id}")
    public ResponseEntity<CompanyEntity> updateCompany(@PathVariable String id, @RequestBody CompanyEntity company) {
        if (!companyService.getCompanyById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        company.setId(id);
        CompanyEntity updatedCompany = companyService.saveCompany(company);
        return new ResponseEntity<>(updatedCompany, HttpStatus.OK);
    }

    // Eliminar una compañía
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable String id) {
        if (!companyService.getCompanyById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Obtener compañía por RUT
    @GetMapping("/rut/{rut}")
    public ResponseEntity<CompanyEntity> getCompanyByRut(@PathVariable String rut) {
        return companyService.getCompanyByRut(rut)
                .map(company -> new ResponseEntity<>(company, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Obtener compañías por tipo
    @GetMapping("/type/{type}")
    public ResponseEntity<List<CompanyEntity>> getCompaniesByType(@PathVariable String type) {
        List<CompanyEntity> companies = companyService.getCompaniesByType(type);
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    // Obtener compañías por método de pago
    @GetMapping("/payment-method/{paymentMethodId}")
    public ResponseEntity<List<CompanyEntity>> getCompaniesByPaymentMethod(@PathVariable String paymentMethodId) {
        List<CompanyEntity> companies = companyService.getCompaniesByPaymentMethod(paymentMethodId);
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    // Obtener compañías por área de cobertura
    @GetMapping("/coverage-area/{coverageAreaId}")
    public ResponseEntity<List<CompanyEntity>> getCompaniesByCoverageArea(@PathVariable String coverageAreaId) {
        List<CompanyEntity> companies = companyService.getCompaniesByCoverageArea(coverageAreaId);
        if (companies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
}
