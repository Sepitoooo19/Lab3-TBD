package bdavanzadas.lab1.mappers;

import bdavanzadas.lab1.entities.OrderDetailsEntity;
import bdavanzadas.lab1.documents.OrderDetailDocument;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;


/**
 * Mapeador para convertir entidades de detalles de pedido a documentos de detalles de pedido.
 * Este mapeador transforma los datos de la entidad de detalles de pedido
 * a un documento que puede ser almacenado en MongoDB.
 */
public class OrderDetailMapper {

    /**
     * Convierte una entidad de detalles de pedido a un documento de detalles de pedido.
     *
     * @param entity Entidad de detalles de pedido a convertir
     * @return Documento de detalles de pedido con los datos de la entidad
     */
    public static OrderDetailDocument fromOrderDetailsEntity(OrderDetailsEntity entity) {
        OrderDetailDocument document = new OrderDetailDocument();

        document.setOrderDetailId(entity.getId());
        document.setOrderId(entity.getOrderId());
        document.setPaymentMethod(entity.getPaymentMethod());
        document.setPrice(entity.getPrice());
        document.setTotalProducts(entity.getTotalProducts());

        return document;


    }


}
