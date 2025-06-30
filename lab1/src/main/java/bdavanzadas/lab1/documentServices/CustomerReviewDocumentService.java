package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.CustomerReviewDocument;
import bdavanzadas.lab1.documentRepositories.CustomerReviewDocumentRepository;
import bdavanzadas.lab1.projections.ReviewHourStatsProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bdavanzadas.lab1.projections.AverageRatingWithNameProjection;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones con reseñas de clientes.
 * Proporciona métodos para buscar, crear, actualizar y eliminar reseñas,
 * así como para obtener análisis estadísticos sobre las valoraciones.
 */
@Service
public class CustomerReviewDocumentService {

    private final CustomerReviewDocumentRepository repository;

    /**
     * Constructor que inyecta el repositorio de reseñas.
     * @param repository Repositorio de reseñas de clientes
     */
    @Autowired
    public CustomerReviewDocumentService(CustomerReviewDocumentRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtiene las reseñas asociadas a una compañía específica.
     * @param companyId ID de la compañía
     * @return Lista de reseñas de la compañía
     */
    public List<CustomerReviewDocument> getByCompanyId(Integer companyId) {
        return repository.findByCompanyId(companyId);
    }

    /**
     * Obtiene las reseñas realizadas por un cliente específico.
     * @param clientId ID del cliente
     * @return Lista de reseñas del cliente
     */
    public List<CustomerReviewDocument> getByClientId(Integer clientId) {
        return repository.findByClientId(clientId);
    }

    /**
     * Verifica si existe una reseña con el ID especificado.
     * @param reviewId ID de la reseña
     * @return true si existe, false en caso contrario
     */
    public boolean existsByReviewId(Integer reviewId) {
        return repository.existsByReviewId(reviewId);
    }

    /**
     * Busca una reseña específica por su ID.
     * @param reviewId ID de la reseña
     * @return Optional con la reseña si existe
     */
    public Optional<CustomerReviewDocument> getByReviewId(Integer reviewId) {
        return repository.findByReviewId(reviewId);
    }

    /**
     * Obtiene todas las reseñas del sistema.
     * @return Lista completa de reseñas
     */
    public List<CustomerReviewDocument> getAll() {
        return repository.findAll();
    }

    /**
     * Guarda o actualiza una reseña.
     * @param doc Documento de la reseña
     * @return Reseña guardada/actualizada
     */
    public CustomerReviewDocument save(CustomerReviewDocument doc) {
        return repository.save(doc);
    }

    /**
     * Elimina una reseña por su ID de documento.
     * @param id ID del documento de la reseña
     */
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    /**
     * Obtiene el promedio de calificaciones por compañía con nombres.
     * @return Lista de proyecciones con:
     *         - companyId: ID de la compañía
     *         - averageRating: Promedio de calificación
     *         - companyName: Nombre de la compañía
     */
    public List<AverageRatingWithNameProjection> getAverageRatingWithCompanyName() {
        return repository.getAverageRatingWithCompanyName();
    }

    /**
     * Busca reseñas que contengan palabras clave específicas.
     * @param keywords Palabras clave a buscar (ej: "demora", "error")
     * @return Lista de reseñas que contienen alguna de las palabras clave
     * @apiNote Las palabras clave se unen en una expresión regular OR (|)
     */
    public List<CustomerReviewDocument> findByKeywords(String... keywords) {
        String joined = String.join("|", keywords);
        return repository.findByCommentContainingKeywords(joined);
    }

    /**
     * Obtiene estadísticas de reseñas agrupadas por hora del día.
     * @return Lista de proyecciones con:
     *         - hour: Hora del día (0-23)
     *         - count: Cantidad de reseñas
     *         - avgRating: Promedio de calificación
     */
    public List<ReviewHourStatsProjection> getReviewStatsByHour() {
        return repository.getReviewStatsByHour();
    }
}