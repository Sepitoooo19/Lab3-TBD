package bdavanzadas.lab1.projections;


/**
 * Proyección para obtener estadísticas de opiniones por hora del día.
 * Esta proyección incluye la hora del día, la cantidad de opiniones
 * y el promedio de rating para cada hora.
 */
public interface ReviewHourStatsProjection {


    /**
     * ID de la hora del día (viene como "_id" desde el aggregation).
     * Representa la hora en formato 0-23.
     */
    Integer get_id();       // hora del día (0-23)


    /**
     * Cantidad de opiniones registradas para esta hora.
     * Este valor se utiliza para determinar la cantidad de opiniones
     * recibidas en esa hora específica.
     */
    Long getCount();        // cantidad de opiniones


    /**
     * Promedio de rating calculado para las opiniones de esta hora.
     * Este valor representa el promedio de las puntuaciones dadas
     * en las opiniones registradas durante esa hora.
     */
    Double getAvgRating();  // promedio de rating
}
