package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.UserNavigationDocument;
import bdavanzadas.lab1.documentRepositories.UserNavigationDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bdavanzadas.lab1.projections.SearchWithoutOrderProjection;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para gestionar la navegación de usuarios.
 * Proporciona métodos para buscar, crear, actualizar y eliminar
 * documentos de navegación de usuarios del sistema.
 */
@Service
public class UserNavigationDocumentService {

    /**
     * Repositorio para acceder a los documentos de navegación de usuarios.
     * Este repositorio permite realizar operaciones CRUD sobre la navegación de usuarios.
     */
    private final UserNavigationDocumentRepository repository;

    /**
     * Constructor que inyecta el repositorio de navegación de usuarios.
     * @param repository Repositorio de navegación de usuarios a utilizar
     */
    @Autowired
    public UserNavigationDocumentService(UserNavigationDocumentRepository repository) {
        this.repository = repository;
    }

    /**
     * Obtiene un documento de navegación de usuario por su id de cliente
     * @param clientId ID del cliente
     @return Lista de documentos de navegación de usuario asociados al ID del cliente
     */
    public List<UserNavigationDocument> getByClientId(Integer clientId) {
        return repository.findByClientId(clientId);
    }

    /**
     * Obtiene todos los documentos de navegación de usuario registrados en el sistema.
     * @return Lista de todos los documentos de navegación de usuario.
     */

    public List<UserNavigationDocument> getAll() {
        return repository.findAll();
    }

    /**
     * Guarda un nuevo documento de navegación de usuario o actualiza uno existente.
     * @param doc Documento de navegación de usuario a guardar
     *            @return Documento de navegación de usuario guardado
     */
    public UserNavigationDocument save(UserNavigationDocument doc) {
        return repository.save(doc);
    }

    /**
     * Elimina un documento de navegación de usuario por su ID.
     * @param id ID del documento de navegación de usuario a eliminar
     *           @return void
     */
    public void deleteById(String id) {
        repository.deleteById(id);
    }


    /**
     * Obtiene la navegacion de usuarios sin contar pedidos.
     * * @return Lista de documentos de navegación de usuario sin pedidos
     *
     */

    public List<UserNavigationDocument> getNonOrderNavigationDocuments() {
        List<String> excludedTypes = Arrays.asList("order_confirmed", "order_failed");
        return repository.findByEventTypeNotIn(excludedTypes);
    }
}
