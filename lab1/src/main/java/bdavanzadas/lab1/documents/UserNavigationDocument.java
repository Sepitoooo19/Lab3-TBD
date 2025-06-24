package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "navegacion_usuarios")
public class UserNavigationDocument {
    private String id;

    private Integer userId;
    private String eventType; // e.g., "search", "click", "filter"
    private Map<String, Object> metadata; // permite flexibilidad para filtros o info adicional
    private LocalDateTime timestamp;
}
