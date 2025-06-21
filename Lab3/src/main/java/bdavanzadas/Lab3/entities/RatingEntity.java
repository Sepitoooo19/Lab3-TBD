package bdavanzadas.Lab3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ratings")
public class RatingEntity {
    private String id;
    private int rating;
    private String comment;
    private LocalDate date;
    private String clientId;
    private String dealerId;
    private String orderId;
}
