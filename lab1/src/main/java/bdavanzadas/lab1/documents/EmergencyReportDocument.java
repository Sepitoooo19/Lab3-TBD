package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;


/**
 *
 * Documento que representa un reporte de emergencia en la base de datos MongoDB.
 * * Contiene información sobre el ID del reporte, ID de la orden asociada,
 * * ID del repartidor, y la ubicación geográfica del reporte.
 *
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "emergency_reports")
public class EmergencyReportDocument {
    private String id;

    @Indexed(unique = true)
    private Integer reportId;
    private Integer orderId;
    private Integer dealerId;
    private GeoJsonPoint location;
}
