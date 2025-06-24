package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "logs_pedidos")
public class OrderLogDocument {
    private String id;

    private Integer orderId;
    private String status;
    private LocalDateTime timestamp;
}
