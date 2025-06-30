package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.CustomerReviewDocument;
import bdavanzadas.lab1.projections.AverageRatingWithNameProjection;
import bdavanzadas.lab1.projections.ReviewHourStatsProjection;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones con reseñas de clientes en MongoDB.
 * Proporciona métodos para consultar y analizar valoraciones de clientes,
 * incluyendo agregaciones complejas para estadísticas de satisfacción.
 */
@Repository
public interface CustomerReviewDocumentRepository extends MongoRepository<CustomerReviewDocument, String> {

    /**
     * Busca reseñas por ID de compañía.
     * @param companyId ID de la compañía a buscar.
     * @return Lista de reseñas asociadas a la compañía.
     */
    List<CustomerReviewDocument> findByCompanyId(Integer companyId);

    /**
     * Busca reseñas por ID de cliente.
     * @param clientId ID del cliente a buscar.
     * @return Lista de reseñas realizadas por el cliente.
     */
    List<CustomerReviewDocument> findByClientId(Integer clientId);

    /**
     * Verifica si existe una reseña con el ID especificado.
     * @param reviewId ID de la reseña a verificar.
     * @return true si existe la reseña, false en caso contrario.
     */
    boolean existsByReviewId(Integer reviewId);

    /**
     * Busca una reseña por su ID único.
     * @param reviewId ID de la reseña a buscar.
     * @return Optional que contiene la reseña si existe.
     */
    Optional<CustomerReviewDocument> findByReviewId(Integer reviewId);

    /**
     * Obtiene el promedio de calificaciones por compañía con nombres asociados.
     * @return Lista de proyecciones con:
     *         - ID de compañía
     *         - Promedio de calificación
     *         - Nombre de la compañía
     * @apiNote Realiza una agregación que:
     *          1. Agrupa por companyId calculando el promedio
     *          2. Hace join con la colección companies
     *          3. Proyecta los campos necesarios
     */
    @Aggregation(pipeline = {
            "{ $group: { _id: \"$companyId\", averageRating: { $avg: \"$rating\" } } }",
            "{ $lookup: { from: \"companies\", localField: \"_id\", foreignField: \"companyId\", as: \"companyInfo\" } }",
            "{ $unwind: \"$companyInfo\" }",
            "{ $project: { _id: 1, averageRating: 1, companyName: \"$companyInfo.name\" } }"
    })
    List<AverageRatingWithNameProjection> getAverageRatingWithCompanyName();

    /**
     * Busca reseñas que contengan palabras clave específicas.
     * @param regex Expresión regular para buscar en los comentarios.
     * @return Lista de reseñas que coinciden con la expresión regular.
     * @example Para buscar "demora" o "error": "(demora|error)"
     */
    @Query("{ 'comment': { $regex: ?0, $options: 'i' } }")
    List<CustomerReviewDocument> findByCommentContainingKeywords(String regex);

    /**
     * Obtiene estadísticas de reseñas agrupadas por hora del día.
     * @return Lista de proyecciones con:
     *         - Hora del día (0-23)
     *         - Cantidad de reseñas
     *         - Promedio de calificación
     * @apiNote La agregación:
     *          1. Extrae la hora de la fecha (zona horaria Santiago)
     *          2. Agrupa por hora calculando conteo y promedio
     *          3. Ordena por hora ascendente
     */
    @Aggregation(pipeline = {
            "{ $project: { hour: { $hour: { date: '$date', timezone: 'America/Santiago' } }, rating: 1 } }",
            "{ $group: { _id: '$hour', count: { $sum: 1 }, avgRating: { $avg: '$rating' } } }",
            "{ $sort: { _id: 1 } }"
    })
    List<ReviewHourStatsProjection> getReviewStatsByHour();
}