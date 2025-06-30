package bdavanzadas.lab1.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;


/**
 *
 * Documento que representa un cliente en la base de datos MongoDB.
 * Contiene información básica del cliente como ID, nombre, RUT,
 * email, teléfono, dirección y ubicación geográfica.
 *
 * */
@Data
@Document(collection = "clients")
public class ClientDocument {
    @Id
    private String id;

    @Indexed(unique = true)
    private Integer clientId;

    private String name;
    private String rut;
    private String email;
    private String phone;
    private String address;
    private GeoJsonPoint location;

    @Indexed(unique = true)
    private Integer userId;
}