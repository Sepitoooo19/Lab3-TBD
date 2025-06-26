package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documentRepositories.OrderDocumentRepository;
import bdavanzadas.lab1.documentRepositories.ProductDocumentRepository;
import bdavanzadas.lab1.documents.CompanyDocument;
import bdavanzadas.lab1.documentRepositories.CompanyDocumentRepository;
import bdavanzadas.lab1.documents.OrderDocument;
import bdavanzadas.lab1.documents.ProductDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyDocumentService {

    private final CompanyDocumentRepository companyRepository;
    private final OrderDocumentRepository orderRepository;
    private final ProductDocumentRepository productRepository;

    @Autowired
    public CompanyDocumentService(CompanyDocumentRepository companyRepository,
                                  OrderDocumentRepository orderRepository,
                                  ProductDocumentRepository productRepository) {
        this.companyRepository = companyRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Optional<CompanyDocument> getByRut(String rut) {
        return companyRepository.findByRut(rut);
    }

    public List<CompanyDocument> getByType(String type) {
        return companyRepository.findByType(type);
    }

    public List<CompanyDocument> getByPaymentMethodId(String paymentMethodId) {
        return companyRepository.findByPaymentMethodId(paymentMethodId);
    }

    public List<CompanyDocument> getByCoverageAreaId(String coverageAreaId) {
        return companyRepository.findByCoverageAreaId(coverageAreaId);
    }

    public boolean existsByCompanyId(Integer companyId) {
        return companyRepository.existsByCompanyId(companyId);
    }

    public Optional<CompanyDocument> getByCompanyId(Integer companyId) {
        return companyRepository.findByCompanyId(companyId).map(clientDoc -> {
            // Este cast es inseguro si el método devuelve un ClientDocument.
            // Lo correcto sería que el repositorio devuelva un Optional<CompanyDocument>.
            // Este fix requiere ajustar el repositorio.
            return (CompanyDocument) null; // <-- este bloque es solo placeholder
        });
    }

    public CompanyDocument save(CompanyDocument company) {
        return companyRepository.save(company);
    }

    public void deleteById(String id) {
        companyRepository.deleteById(id);
    }

    public Optional<CompanyDocument> getById(String id) {
        return companyRepository.findById(id);
    }

    public List<CompanyDocument> getAll() {
        return companyRepository.findAll();
    }


}

