package bdavanzadas.Lab3.services;

import bdavanzadas.Lab3.entities.CompanyEntity;
import bdavanzadas.Lab3.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // Obtener todas las compañías
    public List<CompanyEntity> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Obtener una compañía por ID
    public Optional<CompanyEntity> getCompanyById(String id) {
        return companyRepository.findById(id);
    }

    // Crear o actualizar una compañía
    public CompanyEntity saveCompany(CompanyEntity company) {
        return companyRepository.save(company);
    }

    // Eliminar una compañía por ID
    public void deleteCompany(String id) {
        companyRepository.deleteById(id);
    }

    // Obtener compañía por RUT
    public Optional<CompanyEntity> getCompanyByRut(String rut) {
        return companyRepository.findByRut(rut);
    }

    // Obtener compañías por tipo
    public List<CompanyEntity> getCompaniesByType(String type) {
        return companyRepository.findByType(type);
    }

    // Obtener compañías por método de pago
    public List<CompanyEntity> getCompaniesByPaymentMethod(String paymentMethodId) {
        return companyRepository.findByPaymentMethodId(paymentMethodId);
    }

    // Obtener compañías por área de cobertura
    public List<CompanyEntity> getCompaniesByCoverageArea(String coverageAreaId) {
        return companyRepository.findByCoverageAreaId(coverageAreaId);
    }


}
