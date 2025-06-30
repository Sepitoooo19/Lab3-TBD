package bdavanzadas.lab1.mappers;

import bdavanzadas.lab1.documents.OrderDocument;
import bdavanzadas.lab1.entities.OrdersEntity;
import com.mongodb.client.model.geojson.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


/**
 * Mapeador para convertir entidades de pedidos a documentos de pedidos.
 * Este mapeador transforma los datos de la entidad de pedidos
 * a un documento que puede ser almacenado en MongoDB.
 */
public class OrderMapper {


    /**
     * Convierte una entidad de pedidos a un documento de pedidos.
     *
     * @param entity Entidad de pedidos a convertir
     * @param productIds Lista de IDs de productos asociados al pedido
     * @return Documento de pedidos con los datos de la entidad
     */
    public static OrderDocument fromOrdersEntity(OrdersEntity entity, List<Integer> productIds) {

        List<String> productIdStrings = new ArrayList<>();
        for (Integer id : productIds) {
            productIdStrings.add(String.valueOf(id));
        }

        GeoJsonLineString route = null;
        if (entity.getEstimatedRoute() != null && entity.getEstimatedRoute().startsWith("LINESTRING(")) {
            route = convertWktToGeoJsonLineString(entity.getEstimatedRoute());
        }

        return OrderDocument.builder()
                .orderId(entity.getId())
                .orderDate(convertDateToLocalDateTime(entity.getOrderDate()))
                .deliveryDate(convertDateToLocalDateTime(entity.getDeliveryDate()))
                .status(entity.getStatus())
                .clientId(entity.getClientId())
                .dealerId(entity.getDealerId() != null ? entity.getDealerId() : null)
                .totalPrice(entity.getTotalPrice())
                .estimatedRoute(route)
                .productIds(productIdStrings)
                .build();
    }

     // CORRECTA

    private static GeoJsonLineString convertWktToGeoJsonLineString(String wkt) {
        wkt = wkt.replace("LINESTRING(", "").replace(")", "").trim();
        String[] coordPairs = wkt.split(",");

        List<org.springframework.data.geo.Point> points = new ArrayList<>();
        for (String pair : coordPairs) {
            String[] coords = pair.trim().split(" ");
            double lon = Double.parseDouble(coords[0]);
            double lat = Double.parseDouble(coords[1]);
            points.add(new org.springframework.data.geo.Point(lon, lat)); // (lon, lat)
        }

        return new GeoJsonLineString(points);
    }

    private static java.time.LocalDateTime convertDateToLocalDateTime(java.util.Date date) {
        if (date == null) return null;
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }
}
