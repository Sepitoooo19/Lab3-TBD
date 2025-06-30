package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.PaymentMethodDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones con documentos de métodos de pago en MongoDB.
 * Proporciona métodos para consultar y gestionar los diferentes métodos de pago
 * disponibles en el sistema y su asociación con compañías.
 */
@Repository
public interface PaymentMethodDocumentRepository extends MongoRepository<PaymentMethodDocument, String> {

    /**
     * Busca un método de pago por su tipo único.
     * @param type Tipo del método de pago a buscar (ej: "Tarjeta de Crédito", "Transferencia").
     * @return El documento del método de pago correspondiente al tipo especificado.
     * @note Retorna null si no se encuentra ningún método de pago con el tipo dado.
     */
    PaymentMethodDocument findByType(String type);

    /**
     * Busca todos los métodos de pago asociados a una compañía específica.
     * @param companyId ID de la compañía cuyos métodos de pago se desean consultar.
     * @return Lista de métodos de pago habilitados para la compañía especificada.
     *         Retorna lista vacía si la compañía no tiene métodos asociados.
     */
    @Query("{ 'companyIds': ?0 }")
    List<PaymentMethodDocument> findByCompanyId(String companyId);

    /**
     * Verifica si existe un método de pago con el ID especificado.
     * @param paymentMethodId ID numérico del método de pago a verificar.
     * @return true si existe un método de pago con ese ID, false en caso contrario.
     */
    boolean existsByPaymentMethodId(Integer paymentMethodId);

    /**
     * Busca un método de pago por su ID numérico único.
     * @param paymentMethodId ID numérico del método de pago a buscar.
     * @return Optional que contiene el método de pago si existe.
     */
    Optional<PaymentMethodDocument> findByPaymentMethodId(Integer paymentMethodId);
}