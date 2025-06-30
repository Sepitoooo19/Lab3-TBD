package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.PaymentMethodDocument;
import bdavanzadas.lab1.documentRepositories.PaymentMethodDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones con métodos de pago.
 * Proporciona métodos para buscar, crear, actualizar y eliminar documentos
 * relacionados con los métodos de pago en el sistema.
 */
@Service
public class PaymentMethodDocumentService {

    /**
     * Repositorio para acceder a los documentos de métodos de pago.
     * Este repositorio permite realizar operaciones CRUD sobre los métodos de pago.
     */
    private final PaymentMethodDocumentRepository paymentMethodRepository;

    /**
     * Constructor que inyecta el repositorio de métodos de pago.
     * @param paymentMethodRepository Repositorio de métodos de pago a utilizar
     */
    @Autowired
    public PaymentMethodDocumentService(PaymentMethodDocumentRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }


    /**
     * Obtiene un método de pago por su tipo.
     * @param type Tipo del método de pago
     * @return Documento del método de pago si existe, null en caso contrario
     */
    public PaymentMethodDocument getByType(String type) {
        return paymentMethodRepository.findByType(type);
    }


    /**
     * Obtiene todos los métodos de pago asociados a una empresa por su ID.
     * @param companyId ID de la empresa
     * @return Lista de documentos de métodos de pago asociados a la empresa
     */
    public List<PaymentMethodDocument> getByCompanyId(String companyId) {
        return paymentMethodRepository.findByCompanyId(companyId);
    }

    /**
     * Verifica si existe un método de pago con el ID de método de pago especificado.
     * @param paymentMethodId ID del método de pago
     * @return true si existe, false en caso contrario
     */
    public boolean existsByPaymentMethodId(Integer paymentMethodId) {
        return paymentMethodRepository.existsByPaymentMethodId(paymentMethodId);
    }

    /**
     * Busca un método de pago específico por su ID de método de pago.
     * @param paymentMethodId ID del método de pago
     * @return Optional con el documento del método de pago si existe
     */
    public Optional<PaymentMethodDocument> getByPaymentMethodId(Integer paymentMethodId) {
        return paymentMethodRepository.findByPaymentMethodId(paymentMethodId);
    }

    /**
     * Obtiene un método de pago por su ID de documento.
     * @param id ID del documento del método de pago
     * @return Optional que contiene el documento del método de pago si existe
     */
    public Optional<PaymentMethodDocument> getById(String id) {
        return paymentMethodRepository.findById(id);
    }

    /**
     * Obtiene todos los métodos de pago registrados en el sistema.
     * @return Lista de todos los documentos de métodos de pago
     */
    public List<PaymentMethodDocument> getAll() {
        return paymentMethodRepository.findAll();
    }

    /**
     * Guarda o actualiza un método de pago.
     * @param document Documento del método de pago a guardar o actualizar
     * @return Documento del método de pago guardado/actualizado
     */
    public PaymentMethodDocument save(PaymentMethodDocument document) {
        return paymentMethodRepository.save(document);
    }

    /**
     * Elimina un método de pago por su ID de documento.
     * @param id ID del documento del método de pago a eliminar
     * @throws org.springframework.dao.EmptyResultDataAccessException si no existe el método de pago con el ID especificado
     */
    public void deleteById(String id) {
        paymentMethodRepository.deleteById(id);
    }
}
