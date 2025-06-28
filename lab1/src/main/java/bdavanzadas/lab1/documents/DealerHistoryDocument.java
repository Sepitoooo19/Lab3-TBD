package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "historial_repartidores")
public class DealerHistoryDocument {
    private String id;
    private Integer dealerId;
    private List<LocationHistory> locations;
    private LocalDateTime lastUpdated;
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocationHistory {
        private GeoJsonPoint location;
        private LocalDateTime timestamp;
        private String orderId; // Referencia a la orden que generó esta ubicación
    }
    
    public void addLocation(GeoJsonPoint location, String orderId) {
        if (this.locations == null) {
            this.locations = new ArrayList<>();
        }
        this.locations.add(new LocationHistory(location, LocalDateTime.now(), orderId));
        this.lastUpdated = LocalDateTime.now();
    }
}
