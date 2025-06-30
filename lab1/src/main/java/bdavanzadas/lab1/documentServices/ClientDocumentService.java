package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.ClientDocument;
import bdavanzadas.lab1.documentRepositories.ClientDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones con documentos de clientes.
 * Proporciona métodos para buscar, crear, actualizar y eliminar clientes,
 * así como para consultar información específica sobre ellos.
 */
@Service
public class ClientDocumentService {

    private final ClientDocumentRepository clientRepository;

    /**
     * Constructor que inyecta el repositorio de clientes.
     * @param clientRepository Repositorio de clientes a utilizar
     */
    @Autowired
    public ClientDocumentService(ClientDocumentRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Obtiene todos los clientes registrados en el sistema.
     * @return Lista de todos los documentos de clientes.
     *         Retorna lista vacía si no hay clientes registrados.
     */
    public List<ClientDocument> getAllClients() {
        return clientRepository.findAll();
    }

    /**
     * Busca un cliente por su ID de usuario asociado.
     * @param userId ID del usuario asociado al cliente.
     * @return Optional que contiene el cliente si existe.
     */
    public Optional<ClientDocument> getClientByUserId(String userId) {
        return clientRepository.findByUserId(userId);
    }

    /**
     * Busca un cliente por su RUT único.
     * @param rut RUT del cliente a buscar (formato: "12345678-9").
     * @return Optional que contiene el cliente si existe.
     */
    public Optional<ClientDocument> getClientByRut(String rut) {
        return clientRepository.findByRut(rut);
    }

    /**
     * Obtiene solo el nombre de un cliente por su ID de documento.
     * @param id ID del documento del cliente.
     * @return Nombre del cliente o null si no existe.
     */
    public String getClientNameById(String id) {
        return clientRepository.findNameById(id);
    }

    /**
     * Verifica si existe un cliente con el ID especificado.
     * @param clientId ID numérico del cliente a verificar.
     * @return true si existe un cliente con ese ID, false en caso contrario.
     */
    public boolean existsByClientId(Integer clientId) {
        return clientRepository.existsByClientId(clientId);
    }

    /**
     * Busca un cliente por su ID numérico único.
     * @param clientId ID numérico del cliente a buscar.
     * @return Optional que contiene el cliente si existe.
     */
    public Optional<ClientDocument> getClientByClientId(Integer clientId) {
        return clientRepository.findByClientId(clientId);
    }

    /**
     * Guarda o actualiza un documento de cliente.
     * @param clientDocument Documento del cliente a guardar/actualizar.
     * @return Documento del cliente guardado.
     */
    public ClientDocument saveClient(ClientDocument clientDocument) {
        return clientRepository.save(clientDocument);
    }

    /**
     * Elimina un cliente por su ID de documento.
     * @param id ID del documento del cliente a eliminar.
     * @throws org.springframework.dao.EmptyResultDataAccessException si no existe un cliente con el ID especificado.
     */
    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }
}