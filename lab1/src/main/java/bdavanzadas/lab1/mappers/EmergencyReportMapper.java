package bdavanzadas.lab1.mappers;

import bdavanzadas.lab1.documents.EmergencyReportDocument;
import bdavanzadas.lab1.entities.EmergencyReportEntity;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class EmergencyReportMapper {

    public static EmergencyReportDocument fromEmergencyReportEntity(EmergencyReportEntity entity) {
        EmergencyReportDocument document = new EmergencyReportDocument();

        document.setReportId(entity.getId());



        document.setOrderId(String.valueOf(entity.getOrderId()));
        document.setDealerId(String.valueOf(entity.getDealerId()));

        // Convertir ubicación WKT "POINT(lon lat)" a GeoJsonPoint
        if (entity.getUbication() != null && entity.getUbication().startsWith("POINT(")) {
            String wkt = entity.getUbication()
                    .replace("POINT(", "")
                    .replace(")", "")
                    .trim();

            String[] coords = wkt.split(" ");
            double lon = Double.parseDouble(coords[0]);
            double lat = Double.parseDouble(coords[1]);
            document.setLocation(new GeoJsonPoint(lon, lat));
        }

        return document;
    }
}
