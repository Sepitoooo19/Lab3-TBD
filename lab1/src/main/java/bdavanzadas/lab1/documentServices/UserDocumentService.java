package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.UserDocument;
import bdavanzadas.lab1.documentRepositories.UserDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones con usuarios.
 * Proporciona métodos para buscar, crear, actualizar y eliminar
 * documentos de usuarios del sistema.
 */
@Service
public class UserDocumentService {

    /**
     * Repositorio para acceder a los documentos de usuarios.
     * Este repositorio permite realizar operaciones CRUD sobre los usuarios.
     */
    private final UserDocumentRepository userRepository;

    /**
     * Constructor que inyecta el repositorio de usuarios.
     * @param userRepository Repositorio de usuarios a utilizar
     */
    @Autowired
    public UserDocumentService(UserDocumentRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Obtiene todos los usuarios registrados en el sistema.
     * @return Lista de todos los documentos de usuarios.
     *         Retorna lista vacía si no hay usuarios registrados.
     */
    public List<UserDocument> getAll() {
        return userRepository.findAll();
    }

    /**
     * Obtiene un usuario por su ID.
     * @param id ID del usuario
     * @return Documento del usuario si existe, Optional vacío en caso contrario
     */
    public Optional<UserDocument> getById(String id) {
        return userRepository.findById(id);
    }
    /**
     * Obtiene un usuario por su nombre de usuario.
     * @param username Nombre de usuario
     * @return Documento del usuario si existe, Optional vacío en caso contrario
     */
    public Optional<UserDocument> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    /**
     * Verifica si existe un usuario con el ID de usuario especificado.
     * @param userId ID del usuario
     * @return true si existe, false en caso contrario
     */
    public boolean existsByUserId(Integer userId) {
        return userRepository.existsByUserId(userId);
    }

    /**
     * Busca un usuario específico por su ID de usuario.
     * @param userId ID del usuario
     * @return Optional con el documento del usuario si existe
     */
    public Optional<UserDocument> getByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    /**
     * Guarda o actualiza un usuario.
     * @param user Documento del usuario a guardar
     * @return Documento del usuario guardado
     */
    public UserDocument save(UserDocument user) {
        return userRepository.save(user);
    }

    /**
     * Elimina un usuario por su ID de documento.
     * @param id ID del documento del usuario
     */
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
