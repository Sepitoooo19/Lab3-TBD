package bdavanzadas.lab1.mappers;

import bdavanzadas.lab1.entities.CompanyEntity;
import bdavanzadas.lab1.documents.CompanyDocument;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.stream.Collectors;


/**
 * Mapeador para convertir entidades de empresa a documentos de empresa.
 * Este mapeador transforma los datos de la entidad de empresa
 * a un documento que puede ser almacenado en MongoDB.
 */
public class CompanyMapper {

    /**
     * Convierte una entidad de empresa a un documento de empresa.
     *
     * @param entity Entidad de empresa a convertir
     * @return Documento de empresa con los datos de la entidad
     */
    public static CompanyDocument fromCompanyEntity(CompanyEntity entity) {
        CompanyDocument document = new CompanyDocument();

        document.setCompanyId(entity.getId());
        document.setName(entity.getName());
        document.setEmail(entity.getEmail());
        document.setPhone(entity.getPhone());
        document.setAddress(entity.getAddress());
        document.setRut(entity.getRut());
        document.setType(entity.getType());
        document.setDeliveries(entity.getDeliveries());
        document.setFailedDeliveries(entity.getFailedDeliveries());
        document.setTotalSales(entity.getTotalSales());

        if (entity.getUbication() != null && entity.getUbication().startsWith("POINT(")) {
            String wkt = entity.getUbication().replace("POINT(", "").replace(")", "").trim();
            String[] parts = wkt.split(" ");
            double lon = Double.parseDouble(parts[0]);
            double lat = Double.parseDouble(parts[1]);
            document.setLocation(new GeoJsonPoint(lon, lat));
        }

        // No asignar aquí paymentMethodIds ni coverageAreaIds

        return document;
    }
}