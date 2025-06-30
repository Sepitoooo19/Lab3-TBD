package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.UserNavigationDocument;
import bdavanzadas.lab1.projections.SearchWithoutOrderProjection;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio para operaciones con documentos de navegación de usuarios en MongoDB.
 * Proporciona métodos para consultar y analizar el comportamiento de navegación
 * de los usuarios en la plataforma.
 */
@Repository
public interface UserNavigationDocumentRepository extends MongoRepository<UserNavigationDocument, String> {

    /**
     * Busca todos los registros de navegación de un cliente específico.
     * @param clientId ID del cliente cuyos registros se desean consultar.
     * @return Lista de documentos de navegación asociados al cliente.
     *         Retorna lista vacía si el cliente no tiene registros.
     */
    List<UserNavigationDocument> findByClientId(Integer clientId);

    /**
     * Busca registros de navegación por tipo de evento.
     * @param eventType Tipo de evento a filtrar (ej: "SEARCH", "PRODUCT_VIEW").
     * @return Lista de documentos de navegación que coinciden con el tipo de evento.
     *         Retorna lista vacía si no hay coincidencias.
     */
    List<UserNavigationDocument> findByEventType(String eventType);

    /**
     * Busca registros de navegación por cliente y tipo de evento.
     * @param clientId ID del cliente a filtrar.
     * @param eventType Tipo de evento a filtrar.
     * @return Lista de documentos de navegación que coinciden con ambos criterios.
     *         Retorna lista vacía si no hay coincidencias.
     */
    List<UserNavigationDocument> findByClientIdAndEventType(Integer clientId, String eventType);

    /**
     * Busca registros de navegación excluyendo ciertos tipos de eventos.
     * @param excludedEventTypes Lista de tipos de eventos a excluir.
     * @return Lista de documentos de navegación que no coinciden con los tipos excluidos.
     *         Retorna lista vacía si todos los registros coinciden con los tipos excluidos.
     */
    List<UserNavigationDocument> findByEventTypeNotIn(List<String> excludedEventTypes);
}