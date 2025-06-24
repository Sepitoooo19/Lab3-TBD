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
