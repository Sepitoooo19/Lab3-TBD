package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Documento que representa los detalles de una orden en la base de datos MongoDB.
 * Contiene información sobre el método de pago, total de productos y precio total.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "order_details")
public class OrderDetailDocument {
    private String id;
    @Indexed(unique = true)
    private Integer orderDetailId;
    private Integer orderId;
    private String paymentMethod;
    private int totalProducts;
    private double price;
}
