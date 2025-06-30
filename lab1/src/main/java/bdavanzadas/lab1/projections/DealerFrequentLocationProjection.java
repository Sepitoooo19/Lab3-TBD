package bdavanzadas.lab1.projections;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

/**
 * Proyección para obtener la ubicación frecuente de los repartidores.
 * Esta proyección se utiliza para consultar la ubicación más frecuente
 * de los repartidores en la base de datos MongoDB.
 */
public interface DealerFrequentLocationProjection {

    /**
     * ID del repartidor (viene como "dealerId" desde el aggregation)
     */
    Integer getDealerId();

    /**
     * Ubicación frecuente del repartidor, representada como una lista de coordenadas [lon, lat].
     * Esta ubicación se obtiene a partir de los datos almacenados en MongoDB.
     */
    List<Double> getLocation(); // <- debe coincidir con los datos que devuelve Mongo


    /**
     * Cantidad de veces que se ha registrado esta ubicación para el repartidor.
     * Este valor se utiliza para determinar la frecuencia de la ubicación.
     */
    Long getCount();
}
