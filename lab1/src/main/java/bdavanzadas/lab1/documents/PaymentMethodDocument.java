package bdavanzadas.lab1.documents;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "payment_methods")
public class PaymentMethodDocument {
    private String id;
    private String type;
}