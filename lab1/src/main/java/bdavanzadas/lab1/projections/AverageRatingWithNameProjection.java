package bdavanzadas.lab1.projections;

/**
 * Proyección para obtener el promedio de rating por empresa,
 * incluyendo también el nombre de la empresa desde la colección de compañías.
 */
public interface AverageRatingWithNameProjection {

    /**
     * ID de la empresa (viene como "_id" desde el aggregation)
     */
    Integer get_id();

    /**
     * Promedio de puntuación calculado
     */
    Double getAverageRating();

    /**
     * Nombre de la empresa asociado al companyId
     */
    String getCompanyName();
}
