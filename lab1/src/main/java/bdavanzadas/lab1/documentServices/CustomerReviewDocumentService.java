package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.CustomerReviewDocument;
import bdavanzadas.lab1.documentRepositories.CustomerReviewDocumentRepository;
import bdavanzadas.lab1.projections.ReviewHourStatsProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bdavanzadas.lab1.projections.AverageRatingWithNameProjection;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerReviewDocumentService {

    private final CustomerReviewDocumentRepository repository;

    @Autowired
    public CustomerReviewDocumentService(CustomerReviewDocumentRepository repository) {
        this.repository = repository;
    }

    public List<CustomerReviewDocument> getByCompanyId(Integer companyId) {
        return repository.findByCompanyId(companyId);
    }

    public List<CustomerReviewDocument> getByClientId(Integer clientId) {
        return repository.findByClientId(clientId);
    }

    public boolean existsByReviewId(Integer reviewId) {
        return repository.existsByReviewId(reviewId);
    }

    public Optional<CustomerReviewDocument> getByReviewId(Integer reviewId) {
        return repository.findByReviewId(reviewId);
    }

    public List<CustomerReviewDocument> getAll() {
        return repository.findAll();
    }

    public CustomerReviewDocument save(CustomerReviewDocument doc) {
        return repository.save(doc);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public List<AverageRatingWithNameProjection> getAverageRatingWithCompanyName() {
        return repository.getAverageRatingWithCompanyName();
    }

    public List<CustomerReviewDocument> findByKeywords(String... keywords) {
        String joined = String.join("|", keywords); // genera regex: "demora|error"
        return repository.findByCommentContainingKeywords(joined);
    }

    public List<ReviewHourStatsProjection> getReviewStatsByHour() {
        return repository.getReviewStatsByHour();
    }




}
