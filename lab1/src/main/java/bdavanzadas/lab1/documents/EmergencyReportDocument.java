package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "emergency_reports")
public class EmergencyReportDocument {
    private String id;

    @Indexed(unique = true)
    private Integer reportId;
    private String orderId;
    private String dealerId;
    private GeoJsonPoint location;
}
