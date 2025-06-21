package bdavanzadas.Lab3.services;

import bdavanzadas.Lab3.entities.RatingEntity;
import bdavanzadas.Lab3.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<RatingEntity> findAll() {
        return ratingRepository.findAll();
    }

    public Optional<RatingEntity> findById(String id) {
        return ratingRepository.findById(id);
    }

    public List<RatingEntity> findByClientId(String clientId) {
        return ratingRepository.findByClientId(clientId);
    }

    public List<RatingEntity> findByDealerId(String dealerId) {
        return ratingRepository.findByDealerId(dealerId);
    }

    public RatingEntity save(RatingEntity rating) {
        return ratingRepository.save(rating);
    }

    public RatingEntity update(String id, RatingEntity rating) {
        rating.setId(id);
        return ratingRepository.save(rating);
    }

    public void delete(String id) {
        ratingRepository.deleteById(id);
    }
}
