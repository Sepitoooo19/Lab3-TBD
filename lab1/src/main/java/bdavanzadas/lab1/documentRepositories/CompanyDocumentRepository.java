package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.ClientDocument;
import bdavanzadas.lab1.documents.CompanyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyDocumentRepository extends MongoRepository<CompanyDocument, String> {


    // Búsqueda por RUT (único)
    Optional<CompanyDocument> findByRut(String rut);

    // Búsqueda por tipo de compañía
    List<CompanyDocument> findByType(String type);

    // Búsqueda por método de pago
    @Query("{ 'paymentMethodIds': ?0 }")
    List<CompanyDocument> findByPaymentMethodId(String paymentMethodId);

    // Búsqueda por área de cobertura
    @Query("{ 'coverageAreaIds': ?0 }")
    List<CompanyDocument> findByCoverageAreaId(String coverageAreaId);

    boolean existsByCompanyId(Integer companyId);
    Optional<ClientDocument> findByCompanyId(Integer companyId);


}
