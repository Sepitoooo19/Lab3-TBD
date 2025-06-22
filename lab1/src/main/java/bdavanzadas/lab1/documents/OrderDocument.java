package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "orders")
public class OrderDocument {
    private String id;
    @Indexed(unique = true)
    private Integer orderId;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private String status;
    private String clientId;
    private String dealerId;
    private double totalPrice;
    private GeoJsonLineString estimatedRoute;
    private List<String> productIds;
}