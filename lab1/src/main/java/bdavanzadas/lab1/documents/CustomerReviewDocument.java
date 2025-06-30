package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


/**
 * Documento que representa una opinión de cliente en la base de datos MongoDB.
 * Contiene información sobre el comentario, calificación, fecha y referencias
 * al cliente y empresa asociados a la opinión.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "opiniones_clientes")
public class CustomerReviewDocument {
    private String id;

    @Indexed(unique = true)
    private Integer reviewId;

    private String comment;
    private int rating;
    private LocalDateTime date;

    private Integer clientId;
    private Integer companyId;
}
