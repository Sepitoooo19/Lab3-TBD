package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "historial_repartidores")
public class DealerHistoryDocument {
    private String id;

    private Integer dealerId;
    private GeoJsonPoint location;
    private LocalDateTime timestamp;
}
