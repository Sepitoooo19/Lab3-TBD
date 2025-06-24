package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.DealerHistoryDocument;
import bdavanzadas.lab1.documentRepositories.DealerHistoryDocumentRepository;
import bdavanzadas.lab1.projections.DealerFrequentLocationProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DealerHistoryDocumentService {

    private final DealerHistoryDocumentRepository repository;

    @Autowired
    public DealerHistoryDocumentService(DealerHistoryDocumentRepository repository) {
        this.repository = repository;
    }

    public List<DealerHistoryDocument> getByDealerId(Integer dealerId) {
        return repository.findByDealerId(dealerId);
    }

    public List<DealerHistoryDocument> getAll() {
        return repository.findAll();
    }

    public DealerHistoryDocument save(DealerHistoryDocument history) {
        return repository.save(history);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public List<DealerFrequentLocationProjection> getFrequentLocationsLast7Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date sevenDaysAgo = calendar.getTime();

        return repository.findFrequentLocationsAfter(sevenDaysAgo);
    }
}
