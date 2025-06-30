package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.PaymentMethodDocument;
import bdavanzadas.lab1.documentServices.PaymentMethodDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar métodos de pago.
 * Proporciona endpoints para operaciones CRUD sobre los diferentes métodos
 * de pago disponibles en el sistema.
 */
@RestController
@RequestMapping("/documents/payment-method")
public class PaymentMethodDocumentController {

    /**
     * Servicio para operaciones con métodos de pago.
     */
    private final PaymentMethodDocumentService paymentMethodService;

    /**
     * Constructor que inyecta el servicio de métodos de pago.
     * @param paymentMethodService Servicio para gestionar métodos de pago.
     */
    @Autowired
    public PaymentMethodDocumentController(PaymentMethodDocumentService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    /**
     * Obtiene todos los métodos de pago registrados.
     * @return Lista completa de métodos de pago con código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<PaymentMethodDocument>> getAll() {
        return ResponseEntity.ok(paymentMethodService.getAll());
    }

    /**
     * Obtiene un método de pago por su ID de documento.
     * @param id ID del documento del método de pago.
     * @return Método de pago con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDocument> getById(@PathVariable String id) {
        return paymentMethodService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene un método de pago por su tipo.
     * @param type Tipo de método de pago (ej. "Tarjeta de Crédito", "Transferencia").
     * @return Método de pago con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<PaymentMethodDocument> getByType(@PathVariable String type) {
        PaymentMethodDocument result = paymentMethodService.getByType(type);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    /**
     * Obtiene los métodos de pago asociados a una empresa específica.
     * @param companyId ID de la empresa.
     * @return Lista de métodos de pago de la empresa con código 200 OK.
     */
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<PaymentMethodDocument>> getByCompanyId(@PathVariable String companyId) {
        return ResponseEntity.ok(paymentMethodService.getByCompanyId(companyId));
    }

    /**
     * Verifica si existe un método de pago con el ID especificado.
     * @param paymentMethodId ID del método de pago a verificar.
     * @return true si existe el método de pago, false en caso contrario.
     */
    @GetMapping("/exists/{paymentMethodId}")
    public ResponseEntity<Boolean> existsByPaymentMethodId(@PathVariable Integer paymentMethodId) {
        return ResponseEntity.ok(paymentMethodService.existsByPaymentMethodId(paymentMethodId));
    }

    /**
     * Obtiene un método de pago por su ID numérico.
     * @param paymentMethodId ID numérico del método de pago.
     * @return Método de pago con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/payment-method-id/{paymentMethodId}")
    public ResponseEntity<PaymentMethodDocument> getByPaymentMethodId(@PathVariable Integer paymentMethodId) {
        return paymentMethodService.getByPaymentMethodId(paymentMethodId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo método de pago.
     * @param document Documento del método de pago a crear.
     * @return Método de pago creado con código 200 OK.
     */
    @PostMapping
    public ResponseEntity<PaymentMethodDocument> create(@RequestBody PaymentMethodDocument document) {
        return ResponseEntity.ok(paymentMethodService.save(document));
    }

    /**
     * Elimina un método de pago por su ID de documento.
     * @param id ID del documento a eliminar.
     * @return Respuesta sin contenido con código 204 NO CONTENT.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        paymentMethodService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}