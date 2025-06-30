package bdavanzadas.lab1.mappers;
import bdavanzadas.lab1.entities.PaymentMethodEntity;
import bdavanzadas.lab1.documents.PaymentMethodDocument;

/**
 * Mapeador para convertir entidades de método de pago a documentos de método de pago.
 * Este mapeador transforma los datos de la entidad de método de pago
 * a un documento que puede ser almacenado en MongoDB.
 */
public class PaymentMethodMapper {


    /**
     * Convierte una entidad de método de pago a un documento de método de pago.
     *
     * @param entity Entidad de método de pago a convertir
     * @return Documento de método de pago con los datos de la entidad
     */
    public static PaymentMethodDocument fromPaymentMethodEntity(PaymentMethodEntity entity) {
        PaymentMethodDocument document = new PaymentMethodDocument();

        document.setPaymentMethodId(entity.getId());
        document.setType(entity.getType());

        return document;
    }

}
