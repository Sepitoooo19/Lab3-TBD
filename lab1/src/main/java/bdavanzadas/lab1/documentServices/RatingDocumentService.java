package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.RatingDocument;
import bdavanzadas.lab1.documentRepositories.RatingDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones con valoraciones de clientes.
 * Proporciona métodos para crear, consultar, actualizar y eliminar
 * valoraciones, así como para buscar valoraciones por cliente o repartidor.
 */
@Service
public class RatingDocumentService {

    private final RatingDocumentRepository ratingRepository;

    /**
     * Constructor que inyecta el repositorio de valoraciones.
     * @param ratingRepository Repositorio de valoraciones de clientes
     */
    @Autowired
    public RatingDocumentService(RatingDocumentRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * Obtiene todas las valoraciones registradas en el sistema.
     * @return Lista completa de valoraciones
     */
    public List<RatingDocument> getAll() {
        return ratingRepository.findAll();
    }

    /**
     * Busca una valoración por su ID de documento.
     * @param id ID del documento de la valoración
     * @return Optional que contiene la valoración si existe
     */
    public Optional<RatingDocument> getById(String id) {
        return ratingRepository.findById(id);
    }

    /**
     * Busca valoraciones realizadas por un cliente específico.
     * @param clientId ID del cliente
     * @return Lista de valoraciones del cliente
     */
    public List<RatingDocument> getByClientId(String clientId) {
        return ratingRepository.findByClientId(clientId);
    }

    /**
     * Busca valoraciones recibidas por un repartidor específico.
     * @param dealerId ID del repartidor
     * @return Lista de valoraciones del repartidor
     */
    public List<RatingDocument> getByDealerId(String dealerId) {
        return ratingRepository.findByDealerId(dealerId);
    }

    /**
     * Verifica si existe una valoración con el ID numérico especificado.
     * @param ratingId ID numérico de la valoración
     * @return true si existe, false en caso contrario
     */
    public boolean existsByRatingId(Integer ratingId) {
        return ratingRepository.existsByRatingId(ratingId);
    }

    /**
     * Busca una valoración por su ID numérico único.
     * @param ratingId ID numérico de la valoración
     * @return Optional que contiene la valoración si existe
     */
    public Optional<RatingDocument> getByRatingId(Integer ratingId) {
        return ratingRepository.findByRatingId(ratingId);
    }

    /**
     * Guarda o actualiza una valoración.
     * @param rating Documento de valoración a guardar
     * @return Valoración guardada/actualizada
     */
    public RatingDocument save(RatingDocument rating) {
        return ratingRepository.save(rating);
    }

    /**
     * Elimina una valoración por su ID de documento.
     * @param id ID del documento de la valoración
     */
    public void deleteById(String id) {
        ratingRepository.deleteById(id);
    }
}