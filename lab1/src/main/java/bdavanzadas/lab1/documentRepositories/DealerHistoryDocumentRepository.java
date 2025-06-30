package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.DealerHistoryDocument;
import bdavanzadas.lab1.projections.DealerFrequentLocationProjection;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

/**
 * Repositorio para manejar documentos de historial de repartidores en MongoDB.
 * Proporciona métodos para consultar el historial de ubicaciones de repartidores
 * y analizar las rutas más frecuentes en los últimos 7 días.
 */
@Repository
public interface DealerHistoryDocumentRepository extends MongoRepository<DealerHistoryDocument, String> {

    /**
     * Busca el historial de ubicaciones de un repartidor específico.
     * @param dealerId ID del repartidor cuyo historial se desea consultar.
     * @return Lista de documentos de historial del repartidor.
     */
    List<DealerHistoryDocument> findByDealerId(Integer dealerId);

    /**
     * Analiza las rutas más frecuentes de repartidores desde una fecha específica.
     * @param from Fecha de inicio para el análisis (normalmente 7 días atrás).
     * @return Lista de proyecciones con:
     *         - dealerId: ID del repartidor
     *         - location: Coordenadas de la ubicación frecuente
     *         - count: Número de visitas a esa ubicación
     * @apiNote La agregación realiza las siguientes operaciones:
     *          1. Filtra documentos desde la fecha especificada
     *          2. Agrupa por dealerId y coordenadas de ubicación
     *          3. Cuenta las ocurrencias de cada ubicación
     *          4. Ordena por conteo descendente
     *          5. Proyecta los campos en el formato de salida
     * @example Para analizar últimos 7 días:
     *          findFrequentLocationsAfter(Date.from(LocalDateTime.now().minusDays(7).toInstant(ZoneOffset.UTC)))
     */
    @Aggregation(pipeline = {
            "{ $match: { timestamp: { $gte: ?0 } } }",
            "{ $group: { _id: { dealerId: '$dealerId', location: '$location.coordinates' }, count: { $sum: 1 } } }",
            "{ $sort: { count: -1 } }",
            "{ $project: { _id: 0, dealerId: '$_id.dealerId', location: '$_id.location', count: 1 } }"
    })
    List<DealerFrequentLocationProjection> findFrequentLocationsAfter(Date from);
}