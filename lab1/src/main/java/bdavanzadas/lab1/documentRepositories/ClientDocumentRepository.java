package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.ClientDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;



/**
 *
 * Repositorio para manejar documentos de clientes en MongoDB.
 * Proporciona métodos para buscar clientes por ID, RUT y nombre,
 *
 *
 *
 * */
@Repository
public interface ClientDocumentRepository extends MongoRepository<ClientDocument, String> {


    /**
     * * Método para buscar un cliente por su ID de usuario.
     * * @param userId ID del usuario asociado al cliente.
     * */
    Optional<ClientDocument> findByUserId(String userId);


    /**
     * Método para buscar un cliente por su RUT.
     * @param rut RUT del cliente.
     * @return Cliente encontrado o vacío si no existe.
     */
    Optional<ClientDocument> findByRut(String rut);


    /**
     * Método para buscar el nombre del cliente por su ID.
     * * @param clientId ID del cliente.
     * @return Nombre del cliente si existe, o null si no se encuentra.
     */
    @Query(value = "{ '_id' : ?0 }", fields = "{ 'name' : 1 }")
    String findNameById(String clientId);

    /**
     * Método para verificar si un cliente existe por su ID.
     * @param clientId ID del cliente.
     * @return true si el cliente existe, false en caso contrario.
     */
    boolean existsByClientId(Integer clientId);
    /**
     * Método para buscar un cliente por su ID de cliente.
     * @param clientId ID del cliente.
     * @return Cliente encontrado o vacío si no existe.
     */
    Optional<ClientDocument> findByClientId(Integer clientId);
}