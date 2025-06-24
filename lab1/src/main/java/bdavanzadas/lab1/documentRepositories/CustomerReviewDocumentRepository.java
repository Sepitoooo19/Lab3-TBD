package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.CustomerReviewDocument;
import bdavanzadas.lab1.projections.AverageRatingWithNameProjection;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerReviewDocumentRepository extends MongoRepository<CustomerReviewDocument, String> {
    List<CustomerReviewDocument> findByCompanyId(Integer companyId);
    List<CustomerReviewDocument> findByClientId(Integer clientId);

    boolean existsByReviewId(Integer reviewId);
    Optional<CustomerReviewDocument> findByReviewId(Integer reviewId);

    @Aggregation(pipeline = {
            "{ $group: { _id: \"$companyId\", averageRating: { $avg: \"$rating\" } } }",
            "{ $lookup: { from: \"companies\", localField: \"_id\", foreignField: \"companyId\", as: \"companyInfo\" } }",
            "{ $unwind: \"$companyInfo\" }",
            "{ $project: { _id: 1, averageRating: 1, companyName: \"$companyInfo.name\" } }"
    })
    List<AverageRatingWithNameProjection> getAverageRatingWithCompanyName();

}
