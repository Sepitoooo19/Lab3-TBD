package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.DealerDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio para operaciones con documentos de repartidores en MongoDB.
 * Proporciona métodos para consultar y gestionar la información de los repartidores,
 * incluyendo búsquedas por diferentes criterios únicos.
 */
@Repository
public interface DealerDocumentRepository extends MongoRepository<DealerDocument, String> {

    /**
     * Busca un repartidor por su ID de usuario asociado.
     * @param userId ID del usuario asociado al repartidor.
     * @return Optional que contiene el repartidor si existe.
     */
    Optional<DealerDocument> findByUserId(String userId);

    /**
     * Busca un repartidor por su RUT único.
     * @param rut RUT del repartidor a buscar.
     * @return Optional que contiene el repartidor si existe.
     */
    Optional<DealerDocument> findByRut(String rut);

    /**
     * Obtiene solo el nombre de un repartidor por su ID de documento.
     * @param dealerId ID del documento del repartidor.
     * @return Nombre del repartidor o null si no existe.
     * @apiNote Esta consulta utiliza proyección para devolver solo el campo 'name'.
     */
    @Query(value = "{ '_id' : ?0 }", fields = "{ 'name' : 1 }")
    String findNameById(String dealerId);

    /**
     * Verifica si existe un repartidor con el ID numérico especificado.
     * @param dealerId ID numérico del repartidor a verificar.
     * @return true si existe un repartidor con ese ID, false en caso contrario.
     * @note El parámetro se llama 'clientId' en la implementación pero parece referirse a dealerId.
     */
    boolean existsByDealerId(Integer dealerId);

    /**
     * Busca un repartidor por su ID numérico.
     * @param dealerId ID numérico del repartidor a buscar.
     * @return Optional que contiene el repartidor si existe.
     * @note El parámetro se llama 'clientId' en la implementación pero parece referirse a dealerId.
     */
    Optional<DealerDocument> findByDealerId(Integer dealerId);
}