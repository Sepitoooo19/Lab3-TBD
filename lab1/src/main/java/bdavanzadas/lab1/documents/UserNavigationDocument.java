package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;


/**
 *
 * Documento que representa la navegación de un usuario en la aplicación.
 * Contiene información sobre el ID del cliente, el tipo de evento,
 * metadatos adicionales y la marca de tiempo del evento.
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "navegacion_usuarios")
public class UserNavigationDocument {
    private String id;

    private Integer clientId;
    private String eventType;
    private Map<String, Object> metadata;
    private LocalDateTime timestamp;
}
