package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.CustomerReviewDocument;
import bdavanzadas.lab1.projections.AverageRatingWithNameProjection;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerReviewDocumentRepository extends MongoRepository<CustomerReviewDocument, String> {
    List<CustomerReviewDocument> findByCompanyId(Integer companyId);
    List<CustomerReviewDocument> findByClientId(Integer clientId);

    boolean existsByReviewId(Integer reviewId);
    Optional<CustomerReviewDocument> findByReviewId(Integer reviewId);


    // 1.- Obtener el promedio de puntuación por empresa o farmacia.
    @Aggregation(pipeline = {
            "{ $group: { _id: \"$companyId\", averageRating: { $avg: \"$rating\" } } }",
            "{ $lookup: { from: \"companies\", localField: \"_id\", foreignField: \"companyId\", as: \"companyInfo\" } }",
            "{ $unwind: \"$companyInfo\" }",
            "{ $project: { _id: 1, averageRating: 1, companyName: \"$companyInfo.name\" } }"
    })
    List<AverageRatingWithNameProjection> getAverageRatingWithCompanyName();

    // 2.- Listar las opiniones que contengan palabras clave como 'demora' o 'error'.
    
    @Query("{ 'comment': { $regex: ?0, $options: 'i' } }")
    List<CustomerReviewDocument> findByCommentContainingKeywords(String regex);

}
