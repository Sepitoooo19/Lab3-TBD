package bdavanzadas.Lab3.repositories;

import bdavanzadas.Lab3.entities.PaymentMethodEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends MongoRepository<PaymentMethodEntity, String> {

    /**
     * Encuentra un método de pago por su tipo
     * @param type El tipo de método de pago a buscar
     * @return El método de pago encontrado
     */
    PaymentMethodEntity findByType(String type);

    /**
     * Encuentra todos los métodos de pago asociados a una compañía
     * @param companyId El ID de la compañía
     * @return Lista de métodos de pago asociados a la compañía
     */
    @Query("{ 'companyIds': ?0 }")
    List<PaymentMethodEntity> findByCompanyId(String companyId);
}
