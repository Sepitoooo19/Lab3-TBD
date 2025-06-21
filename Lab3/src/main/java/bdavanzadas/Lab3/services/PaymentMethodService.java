package bdavanzadas.Lab3.services;

import bdavanzadas.Lab3.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bdavanzadas.Lab3.entities.PaymentMethodEntity;

import java.util.List;
import java.util.Optional;


@Service
public class PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    /**
     * Obtiene todos los métodos de pago
     * @return Lista de todos los métodos de pago
     */
    public List<PaymentMethodEntity> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    /**
     * Busca un método de pago por su ID
     * @param id ID del método de pago
     * @return Optional con el método de pago si existe
     */
    public Optional<PaymentMethodEntity> getPaymentMethodById(String id) {
        return paymentMethodRepository.findById(id);
    }

    /**
     * Busca un método de pago por su tipo
     * @param type Tipo de método de pago
     * @return El método de pago encontrado o null
     */
    public PaymentMethodEntity getPaymentMethodByType(String type) {
        return paymentMethodRepository.findByType(type);
    }

    /**
     * Crea o actualiza un método de pago
     * @param paymentMethod Método de pago a guardar
     * @return Método de pago guardado
     */
    public PaymentMethodEntity savePaymentMethod(PaymentMethodEntity paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }


    /**
     * Elimina un método de pago por su ID
     * @param id ID del método de pago a eliminar
     */
    public void deletePaymentMethod(String id) {
        paymentMethodRepository.deleteById(id);
    }


    /**
     * Obtiene todos los métodos de pago de una compañía específica
     * @param companyId ID de la compañía
     * @return Lista de métodos de pago de la compañía
     */
    public List<PaymentMethodEntity> getPaymentMethodsByCompanyId(String companyId) {
        return paymentMethodRepository.findByCompanyId(companyId);
    }

    /**
     * Actualiza un método de pago existente.
     * */

    public PaymentMethodEntity updatePaymentMethod(PaymentMethodEntity paymentMethod) {
        if (paymentMethodRepository.existsById(paymentMethod.getId())) {
            return paymentMethodRepository.save(paymentMethod);
        } else {
            throw new IllegalArgumentException("Payment method with ID " + paymentMethod.getId() + " does not exist.");
        }

    }

}
