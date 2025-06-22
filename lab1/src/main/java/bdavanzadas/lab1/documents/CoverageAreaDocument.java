package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "coverage_areas")
public class CoverageAreaDocument {
    private String id;

    @Indexed(unique = true)
    private Integer coverageAreaId;
    private String name;
    private GeoJsonPolygon coverageArea;
}
