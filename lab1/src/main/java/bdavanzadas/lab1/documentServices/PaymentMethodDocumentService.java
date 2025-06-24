package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.PaymentMethodDocument;
import bdavanzadas.lab1.documentRepositories.PaymentMethodDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodDocumentService {

    private final PaymentMethodDocumentRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodDocumentService(PaymentMethodDocumentRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    public PaymentMethodDocument getByType(String type) {
        return paymentMethodRepository.findByType(type);
    }

    public List<PaymentMethodDocument> getByCompanyId(String companyId) {
        return paymentMethodRepository.findByCompanyId(companyId);
    }

    public boolean existsByPaymentMethodId(Integer paymentMethodId) {
        return paymentMethodRepository.existsByPaymentMethodId(paymentMethodId);
    }

    public Optional<PaymentMethodDocument> getByPaymentMethodId(Integer paymentMethodId) {
        return paymentMethodRepository.findByPaymentMethodId(paymentMethodId);
    }

    public Optional<PaymentMethodDocument> getById(String id) {
        return paymentMethodRepository.findById(id);
    }

    public List<PaymentMethodDocument> getAll() {
        return paymentMethodRepository.findAll();
    }

    public PaymentMethodDocument save(PaymentMethodDocument document) {
        return paymentMethodRepository.save(document);
    }

    public void deleteById(String id) {
        paymentMethodRepository.deleteById(id);
    }
}
