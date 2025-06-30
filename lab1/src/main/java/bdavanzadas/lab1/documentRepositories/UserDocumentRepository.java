package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio para operaciones con documentos de usuarios en MongoDB.
 * Proporciona métodos para consultar y verificar la existencia de usuarios
 * del sistema mediante diferentes criterios de búsqueda.
 */
@Repository
public interface UserDocumentRepository extends MongoRepository<UserDocument, String> {

    /**
     * Busca un usuario por su nombre de usuario único.
     * @param username Nombre de usuario a buscar (case-sensitive).
     * @return Optional que contiene el usuario si existe.
     */
    Optional<UserDocument> findByUsername(String username);

    /**
     * Verifica si existe un usuario con el ID numérico especificado.
     * @param userId ID numérico del usuario a verificar.
     * @return true si existe un usuario con ese ID, false en caso contrario.
     */
    boolean existsByUserId(Integer userId);

    /**
     * Busca un usuario por su ID numérico único.
     * @param userId ID numérico del usuario a buscar.
     * @return Optional que contiene el usuario si existe.
     */
    Optional<UserDocument> findByUserId(Integer userId);
}