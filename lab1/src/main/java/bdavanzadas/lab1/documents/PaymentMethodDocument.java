package bdavanzadas.lab1.documents;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payment_methods")
public class PaymentMethodDocument {
    private String id;
    @Indexed(unique = true)
    private Integer paymentMethodId;
    private String type;
}