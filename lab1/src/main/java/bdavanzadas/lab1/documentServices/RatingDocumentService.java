package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.RatingDocument;
import bdavanzadas.lab1.documentRepositories.RatingDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingDocumentService {

    private final RatingDocumentRepository ratingRepository;

    @Autowired
    public RatingDocumentService(RatingDocumentRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<RatingDocument> getAll() {
        return ratingRepository.findAll();
    }

    public Optional<RatingDocument> getById(String id) {
        return ratingRepository.findById(id);
    }

    public List<RatingDocument> getByClientId(String clientId) {
        return ratingRepository.findByClientId(clientId);
    }

    public List<RatingDocument> getByDealerId(String dealerId) {
        return ratingRepository.findByDealerId(dealerId);
    }

    public boolean existsByRatingId(Integer ratingId) {
        return ratingRepository.existsByRatingId(ratingId);
    }

    public Optional<RatingDocument> getByRatingId(Integer ratingId) {
        return ratingRepository.findByRatingId(ratingId);
    }

    public RatingDocument save(RatingDocument rating) {
        return ratingRepository.save(rating);
    }

    public void deleteById(String id) {
        ratingRepository.deleteById(id);
    }
}
