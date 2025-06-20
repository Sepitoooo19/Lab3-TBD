package bdavanzadas.Lab3.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
@Document(collection = "clients")
public class ClientEntity {
    @Id
    private String id;

    private String name;
    private String rut;
    private String email;
    private String phone;
    private String address;
    private GeoJsonPoint location;

    @Indexed(unique = true)
    private String userId; // Relación con User
}