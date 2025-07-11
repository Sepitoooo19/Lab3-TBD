package bdavanzadas.lab1.mappers;


import bdavanzadas.lab1.entities.DealerEntity;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import bdavanzadas.lab1.documents.DealerDocument;


/**
 * Mapeador para convertir entidades de repartidor a documentos de repartidor.
 * Este mapeador transforma los datos de la entidad de repartidor
 * a un documento que puede ser almacenado en MongoDB.
 */
public class DealerMapper {


    /**
     * Convierte una entidad de repartidor a un documento de repartidor.
     *
     * @param entity Entidad de repartidor a convertir
     * @return Documento de repartidor con los datos de la entidad
     */
    public static DealerDocument fromDealerEntity(DealerEntity entity) {
        if (entity == null) {
            return null;
        }

        DealerDocument document = new DealerDocument();

        document.setDealerId(entity.getId());

        document.setName(entity.getName());
        document.setRut(entity.getRut());
        document.setEmail(entity.getEmail());
        document.setPhone(entity.getPhone());
        document.setPlate(entity.getPlate());
        document.setVehicle(entity.getVehicle());

        document.setUserId(entity.getUserId());

        if (entity.getUbication() != null && entity.getUbication().startsWith("POINT(")) {
            String wkt = entity.getUbication()
                    .replace("POINT(", "")
                    .replace(")", "")
                    .trim(); // ej: "-70.12345 -33.45678"
            String[] parts = wkt.split(" ");
            double lon = Double.parseDouble(parts[0]);
            double lat = Double.parseDouble(parts[1]);
            document.setLocation(new GeoJsonPoint(lon, lat));
        }


        return document;
    }
}
