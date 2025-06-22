package bdavanzadas.lab1.mappers;
import bdavanzadas.lab1.entities.PaymentMethodEntity;
import bdavanzadas.lab1.documents.PaymentMethodDocument;
public class PaymentMethodMapper {

    public static PaymentMethodDocument fromPaymentMethodEntity(PaymentMethodEntity entity) {
        PaymentMethodDocument document = new PaymentMethodDocument();

        document.setPaymentMethodId(entity.getId());
        document.setType(entity.getType());

        return document;
    }

}
