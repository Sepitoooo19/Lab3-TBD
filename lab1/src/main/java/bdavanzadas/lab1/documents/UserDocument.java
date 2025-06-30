package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 *  Documento que representa un usuario en la base de datos MongoDB.
 *  * Contiene información sobre el ID del usuario, nombre de usuario, contraseña y rol.
 *
 *
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")  // Nombre de la colección en MongoDB
public class UserDocument {
    @Id
    private String id;  // MongoDB usa String o ObjectId para el ID (en lugar de "SERIAL")

    @Indexed(unique = true)
    private Integer userId;

    @Indexed(unique = true)  // Equivalente a UNIQUE en PostgreSQL
    private String username;

    private String password;
    private String role;  // ADMIN, CLIENT, DEALER (en MongoDB no hay CHECK, se valida en la aplicación)
}


