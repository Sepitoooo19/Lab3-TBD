package bdavanzadas.Lab3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
public class OrderEntity {
    private String id;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String status;
    private String clientId;
    private String dealerId;
    private double totalPrice;
    private GeoJsonLineString estimatedRoute;
    private List<String> productIds;
}