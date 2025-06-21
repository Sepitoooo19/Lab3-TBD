package bdavanzadas.Lab3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order_details")
public class OrderDetailEntity {
    private String id;
    private String orderId;
    private String paymentMethod;
    private int totalProducts;
    private double price;
}
