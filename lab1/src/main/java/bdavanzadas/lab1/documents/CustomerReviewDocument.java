package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

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
    private LocalDate date;

    private Integer clientId;
    private Integer companyId;
}
