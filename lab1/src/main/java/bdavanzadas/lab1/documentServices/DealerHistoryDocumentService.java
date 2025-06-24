package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.DealerHistoryDocument;
import bdavanzadas.lab1.documentRepositories.DealerHistoryDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
