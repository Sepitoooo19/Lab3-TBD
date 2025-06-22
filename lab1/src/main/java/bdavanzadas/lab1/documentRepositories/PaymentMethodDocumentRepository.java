package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.PaymentMethodDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodDocumentRepository extends MongoRepository<PaymentMethodDocument, String> {

    /**
     * Encuentra un método de pago por su tipo
     * @param type El tipo de método de pago a buscar
     * @return El método de pago encontrado
     */
    PaymentMethodDocument findByType(String type);

    /**
     * Encuentra todos los métodos de pago asociados a una compañía
     * @param companyId El ID de la compañía
     * @return Lista de métodos de pago asociados a la compañía
     */
    @Query("{ 'companyIds': ?0 }")
    List<PaymentMethodDocument> findByCompanyId(String companyId);


    boolean existsByPaymentMethodId(Integer paymentMethodId);
    Optional<PaymentMethodDocument> findByPaymentMethodId(Integer paymentMethodId);
}