package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order_details")
public class OrderDetailDocument {
    private String id;
    @Indexed(unique = true)
    private Integer orderDetailId;
    private String orderId;
    private String paymentMethod;
    private int totalProducts;
    private double price;
}
