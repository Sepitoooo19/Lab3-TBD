package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.PaymentMethodDocument;
import bdavanzadas.lab1.documentServices.PaymentMethodDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents/payment-method")
public class PaymentMethodDocumentController {

    private final PaymentMethodDocumentService paymentMethodService;

    @Autowired
    public PaymentMethodDocumentController(PaymentMethodDocumentService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodDocument>> getAll() {
        return ResponseEntity.ok(paymentMethodService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodDocument> getById(@PathVariable String id) {
        return paymentMethodService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<PaymentMethodDocument> getByType(@PathVariable String type) {
        PaymentMethodDocument result = paymentMethodService.getByType(type);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<PaymentMethodDocument>> getByCompanyId(@PathVariable String companyId) {
        return ResponseEntity.ok(paymentMethodService.getByCompanyId(companyId));
    }

    @GetMapping("/exists/{paymentMethodId}")
    public ResponseEntity<Boolean> existsByPaymentMethodId(@PathVariable Integer paymentMethodId) {
        return ResponseEntity.ok(paymentMethodService.existsByPaymentMethodId(paymentMethodId));
    }

    @GetMapping("/payment-method-id/{paymentMethodId}")
    public ResponseEntity<PaymentMethodDocument> getByPaymentMethodId(@PathVariable Integer paymentMethodId) {
        return paymentMethodService.getByPaymentMethodId(paymentMethodId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PaymentMethodDocument> create(@RequestBody PaymentMethodDocument document) {
        return ResponseEntity.ok(paymentMethodService.save(document));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        paymentMethodService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
