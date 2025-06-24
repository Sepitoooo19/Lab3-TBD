package bdavanzadas.lab1.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
@Document(collection = "dealers")
public class DealerDocument {
    @Id
    private String id;

    @Indexed(unique = true)
    private Integer dealerId;

    private String name;
    private String rut;
    private String email;
    private String phone;
    private String vehicle;
    private String plate;
    private GeoJsonPoint location;

    @Indexed(unique = true)
    private Integer userId; // Relación con User
}