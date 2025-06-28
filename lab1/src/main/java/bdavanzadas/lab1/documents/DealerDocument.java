package bdavanzadas.lab1.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;
import java.util.Date;

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

    private GeoJsonLineString mostFrequentRoute; // Ruta más frecuente del repartidor
    private Date routeLastUpdated; // Cuándo se actualizó por última vez la ruta

    /**
     * Actualiza la ruta más frecuente del repartidor
     * @param route La ruta más frecuente
     * @return true si la ruta fue actualizada, false si no hubo cambios
     */
    public boolean updateMostFrequentRoute(GeoJsonLineString route) {
        if (!route.equals(this.mostFrequentRoute)) {
            this.mostFrequentRoute = route;
            this.routeLastUpdated = new Date();
            return true;
        }
        return false;
    }
}