package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


/**
 * Documento que representa una calificación de un pedido en la base de datos MongoDB.
 * Contiene información sobre la calificación, comentario, fecha y referencias
 * al cliente, concesionario y pedido asociados a la calificación.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ratings")
public class RatingDocument {
    private String id;
    @Indexed(unique = true)
    private Integer ratingId;
    private int rating;
    private String comment;
    private LocalDate date;
    private Integer clientId;
    private Integer dealerId;
    private Integer orderId;
}
