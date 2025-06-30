package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


/**
 * Documento que representa un registro de cambios de estado de un pedido en la base de datos MongoDB.
 * Contiene información sobre el ID del pedido, el estado del pedido y la fecha y hora del cambio.
 */
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
