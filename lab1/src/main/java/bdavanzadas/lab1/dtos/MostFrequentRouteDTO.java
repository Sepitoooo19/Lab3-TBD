package bdavanzadas.lab1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonLineString;

@Data
@AllArgsConstructor
public class MostFrequentRouteDTO {
    private Integer dealerId;
    private GeoJsonLineString route;
    private Long count;
}
