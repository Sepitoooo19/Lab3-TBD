package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.DealerHistoryDocument;
import bdavanzadas.lab1.projections.DealerFrequentLocationProjection;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface DealerHistoryDocumentRepository extends MongoRepository<DealerHistoryDocument, String> {
    List<DealerHistoryDocument> findByDealerId(Integer dealerId);


    // 4.- Analizar las rutas más frecuentes de repartidores en los últimos 7 días.
    @Aggregation(pipeline = {
            "{ $match: { timestamp: { $gte: ?0 } } }",
            "{ $group: { _id: { dealerId: '$dealerId', location: '$location.coordinates' }, count: { $sum: 1 } } }",
            "{ $sort: { count: -1 } }",
            "{ $project: { _id: 0, dealerId: '$_id.dealerId', location: '$_id.location', count: 1 } }"
    })
    List<DealerFrequentLocationProjection> findFrequentLocationsAfter(Date from);
}

