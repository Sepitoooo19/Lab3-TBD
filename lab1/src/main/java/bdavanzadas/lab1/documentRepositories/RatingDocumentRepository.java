package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.RatingDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones con documentos de calificaciones en MongoDB.
 * Proporciona métodos para consultar y gestionar las valoraciones que los
 * clientes asignan a los distribuidores o servicios del sistema.
 */
@Repository
public interface RatingDocumentRepository extends MongoRepository<RatingDocument, String> {

    /**
     * Busca todas las calificaciones realizadas por un cliente específico.
     * @param clientId ID del cliente cuyas calificaciones se desean consultar.
     * @return Lista de calificaciones realizadas por el cliente.
     *         Retorna lista vacía si el cliente no ha realizado calificaciones.
     */
    List<RatingDocument> findByClientId(String clientId);

    /**
     * Busca todas las calificaciones recibidas por un distribuidor específico.
     * @param dealerId ID del distribuidor cuyas calificaciones se desean consultar.
     * @return Lista de calificaciones asociadas al distribuidor.
     *         Retorna lista vacía si el distribuidor no tiene calificaciones.
     */
    List<RatingDocument> findByDealerId(String dealerId);

    /**
     * Verifica si existe una calificación con el ID numérico especificado.
     * @param ratingId ID numérico de la calificación a verificar.
     * @return true si existe una calificación con ese ID, false en caso contrario.
     */
    boolean existsByRatingId(Integer ratingId);

    /**
     * Busca una calificación específica por su ID numérico único.
     * @param ratingId ID numérico de la calificación a buscar.
     * @return Optional que contiene la calificación si existe.
     */
    Optional<RatingDocument> findByRatingId(Integer ratingId);
}