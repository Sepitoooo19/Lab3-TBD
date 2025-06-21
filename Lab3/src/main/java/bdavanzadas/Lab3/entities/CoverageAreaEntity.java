package bdavanzadas.Lab3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "coverage_areas")
public class CoverageAreaEntity {
    private String id;
    private String name;
    private GeoJsonPolygon coverageArea;
}
