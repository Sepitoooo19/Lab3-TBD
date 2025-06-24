package bdavanzadas.lab1.projections;

public interface ReviewHourStatsProjection {
    Integer get_id();       // hora del día (0-23)
    Long getCount();        // cantidad de opiniones
    Double getAvgRating();  // promedio de rating
}
