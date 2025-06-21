package bdavanzadas.Lab3.repositories;

import bdavanzadas.Lab3.entities.CompanyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends MongoRepository<CompanyEntity, String> {


    // Búsqueda por RUT (único)
    Optional<CompanyEntity> findByRut(String rut);

    // Búsqueda por tipo de compañía
    List<CompanyEntity> findByType(String type);

    // Búsqueda por método de pago
    @Query("{ 'paymentMethodIds': ?0 }")
    List<CompanyEntity> findByPaymentMethodId(String paymentMethodId);

    // Búsqueda por área de cobertura
    @Query("{ 'coverageAreaIds': ?0 }")
    List<CompanyEntity> findByCoverageAreaId(String coverageAreaId);
}
