package bdavanzadas.lab1.mappers;

import bdavanzadas.lab1.entities.OrderDetailsEntity;
import bdavanzadas.lab1.documents.OrderDetailDocument;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class OrderDetailMapper {
    public static OrderDetailDocument fromOrderDetailsEntity(OrderDetailsEntity entity) {
        OrderDetailDocument document = new OrderDetailDocument();

        document.setOrderDetailId(entity.getId());
        document.setOrderId(String.valueOf(entity.getOrderId()));
        document.setPaymentMethod(entity.getPaymentMethod());
        document.setPrice(entity.getPrice());
        document.setTotalProducts(entity.getTotalProducts());

        return document;


    }


}
