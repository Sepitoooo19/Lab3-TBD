package bdavanzadas.Lab3.controllers;

import bdavanzadas.Lab3.entities.PaymentMethodEntity;
import bdavanzadas.Lab3.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    // Obtener todos los métodos de pago
    @GetMapping
    public ResponseEntity<List<PaymentMethodEntity>> getAllPaymentMethods() {
        List<PaymentMethodEntity> paymentMethods = paymentMethodService.getAllPaymentMethods();
        return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
    }

    // Obtener un método de pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodEntity> getPaymentMethodById(@PathVariable String id) {
        return paymentMethodService.getPaymentMethodById(id)
                .map(paymentMethod -> new ResponseEntity<>(paymentMethod, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Obtener un método de pago por tipo
    @GetMapping("/type/{type}")
    public ResponseEntity<PaymentMethodEntity> getPaymentMethodByType(@PathVariable String type) {
        PaymentMethodEntity paymentMethod = paymentMethodService.getPaymentMethodByType(type);
        if (paymentMethod != null) {
            return new ResponseEntity<>(paymentMethod, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Crear un nuevo método de pago
    @PostMapping
    public ResponseEntity<PaymentMethodEntity> createPaymentMethod(@RequestBody PaymentMethodEntity paymentMethod) {
        PaymentMethodEntity createdPaymentMethod = paymentMethodService.savePaymentMethod(paymentMethod);
        return new ResponseEntity<>(createdPaymentMethod, HttpStatus.CREATED);
    }



    // Eliminar un método de pago
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable String id) {
        paymentMethodService.deletePaymentMethod(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Obtener métodos de pago por compañía
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<PaymentMethodEntity>> getPaymentMethodsByCompanyId(@PathVariable String companyId) {
        List<PaymentMethodEntity> paymentMethods = paymentMethodService.getPaymentMethodsByCompanyId(companyId);
        return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
    }

    // Actualizar un método de pago
    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodEntity> updatePaymentMethod(@PathVariable String id, @RequestBody PaymentMethodEntity paymentMethod) {
        paymentMethod.setId(id); // Asegurarse de que el ID del objeto coincide con el del endpoint
        PaymentMethodEntity updatedPaymentMethod = paymentMethodService.savePaymentMethod(paymentMethod);
        return new ResponseEntity<>(updatedPaymentMethod, HttpStatus.OK);
    }




}