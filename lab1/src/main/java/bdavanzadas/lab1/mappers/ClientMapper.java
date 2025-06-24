package bdavanzadas.lab1.mappers;

import bdavanzadas.lab1.entities.ClientEntity;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import bdavanzadas.lab1.documents.ClientDocument;

public class ClientMapper {

    public static ClientDocument fromClientEntity(ClientEntity entity) {
        ClientDocument doc = new ClientDocument();

        doc.setClientId(entity.getId());  // Asignar el id de Postgres

        doc.setName(entity.getName());
        doc.setRut(entity.getRut());
        doc.setEmail(entity.getEmail());
        doc.setPhone(entity.getPhone());
        doc.setAddress(entity.getAddress());
        doc.setUserId(entity.getUserId());

        if (entity.getUbication() != null && entity.getUbication().startsWith("POINT(")) {
            String wkt = entity.getUbication()
                    .replace("POINT(", "")
                    .replace(")", "")
                    .trim();
            String[] parts = wkt.split(" ");
            double lon = Double.parseDouble(parts[0]);
            double lat = Double.parseDouble(parts[1]);
            doc.setLocation(new GeoJsonPoint(lon, lat));
        }

        return doc;
    }
}