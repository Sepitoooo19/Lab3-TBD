package bdavanzadas.lab1.mappers;

import bdavanzadas.lab1.entities.CoverageAreaEntity;
import bdavanzadas.lab1.documents.CoverageAreaDocument;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;

import java.util.ArrayList;
import java.util.List;

public class CoverageAreaMapper {

    public static CoverageAreaDocument fromCoverageAreaEntity(CoverageAreaEntity entity) {
        CoverageAreaDocument document = new CoverageAreaDocument();

        document.setCoverageAreaId(entity.getId());
        document.setName(entity.getName());

        if (entity.getCoverageArea() != null && entity.getCoverageArea().startsWith("POLYGON((")) {
            String wkt = entity.getCoverageArea()
                    .replace("POLYGON((", "")
                    .replace("))", "")
                    .trim();

            String[] pointsStr = wkt.split(",");
            List<Point> points = new ArrayList<>();

            for (String pointStr : pointsStr) {
                String[] coords = pointStr.trim().split(" ");
                double lon = Double.parseDouble(coords[0]);
                double lat = Double.parseDouble(coords[1]);
                points.add(new Point(lat, lon));  // lat, lon orden para GeoJson
            }

            // GeoJsonPolygon acepta una lista simple de puntos para el anillo exterior
            document.setCoverageArea(new GeoJsonPolygon(points));
        }

        return document;
    }
}
