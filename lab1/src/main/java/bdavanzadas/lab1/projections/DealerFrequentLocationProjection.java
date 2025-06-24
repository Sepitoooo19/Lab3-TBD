package bdavanzadas.lab1.projections;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public interface DealerFrequentLocationProjection {

    Integer getDealerId();
    List<Double> getLocation(); // <- debe coincidir con los datos que devuelve Mongo
    Long getCount();
}
