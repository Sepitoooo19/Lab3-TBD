package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.ClientDocument;
import bdavanzadas.lab1.documents.CompanyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones con documentos de compañías en MongoDB.
 * Proporciona métodos para consultar y gestionar empresas en la base de datos.
 */
@Repository
public interface CompanyDocumentRepository extends MongoRepository<CompanyDocument, String> {

    /**
     * Busca una compañía por su RUT único.
     * @param rut RUT de la compañía a buscar.
     * @return Optional que contiene la compañía si existe.
     */
    Optional<CompanyDocument> findByRut(String rut);

    /**
     * Busca compañías por su tipo.
     * @param type Tipo de compañía a buscar (ej: "Retail", "Logística").
     * @return Lista de compañías que coinciden con el tipo especificado.
     */
    List<CompanyDocument> findByType(String type);

    /**
     * Busca compañías que aceptan un método de pago específico.
     * @param paymentMethodId ID del método de pago a buscar.
     * @return Lista de compañías que aceptan el método de pago.
     */
    @Query("{ 'paymentMethodIds': ?0 }")
    List<CompanyDocument> findByPaymentMethodId(String paymentMethodId);

    /**
     * Busca compañías que operan en un área de cobertura específica.
     * @param coverageAreaId ID del área de cobertura a buscar.
     * @return Lista de compañías que cubren el área especificada.
     */
    @Query("{ 'coverageAreaIds': ?0 }")
    List<CompanyDocument> findByCoverageAreaId(String coverageAreaId);

    /**
     * Verifica si existe una compañía con el ID especificado.
     * @param companyId ID de la compañía a verificar.
     * @return true si existe una compañía con ese ID, false en caso contrario.
     */
    boolean existsByCompanyId(Integer companyId);

    /**
     * Busca un cliente asociado a una compañía por ID de compañía.
     * @param companyId ID de la compañía a buscar.
     * @return Optional que contiene el cliente asociado si existe.
     * @apiNote Este método parece tener un problema de tipo de retorno (ClientDocument vs CompanyDocument).
     */
    Optional<ClientDocument> findByCompanyId(Integer companyId);
}