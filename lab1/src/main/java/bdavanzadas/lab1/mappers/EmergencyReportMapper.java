package bdavanzadas.lab1.mappers;

import bdavanzadas.lab1.documents.EmergencyReportDocument;
import bdavanzadas.lab1.entities.EmergencyReportEntity;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;



/**
 * Mapeador para convertir entidades de reporte de emergencia a documentos de reporte de emergencia.
 * Este mapeador transforma los datos de la entidad de reporte de emergencia
 * a un documento que puede ser almacenado en MongoDB.
 */
public class EmergencyReportMapper {


    /**
     * Convierte una entidad de reporte de emergencia a un documento de reporte de emergencia.
     *
     * @param entity Entidad de reporte de emergencia a convertir
     * @return Documento de reporte de emergencia con los datos de la entidad
     */
    public static EmergencyReportDocument fromEmergencyReportEntity(EmergencyReportEntity entity) {
        EmergencyReportDocument document = new EmergencyReportDocument();

        document.setReportId(entity.getId());



        document.setOrderId(entity.getOrderId());
        document.setDealerId(entity.getDealerId());

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
