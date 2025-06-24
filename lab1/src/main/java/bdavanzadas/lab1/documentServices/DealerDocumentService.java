package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.DealerDocument;
import bdavanzadas.lab1.documentRepositories.DealerDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealerDocumentService {

    private final DealerDocumentRepository dealerRepository;

    @Autowired
    public DealerDocumentService(DealerDocumentRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    //get all
    public List<DealerDocument> getAll() {
        return dealerRepository.findAll();
    }


    public Optional<DealerDocument> getByUserId(String userId) {
        return dealerRepository.findByUserId(userId);
    }

    public Optional<DealerDocument> getByRut(String rut) {
        return dealerRepository.findByRut(rut);
    }

    public String getNameById(String id) {
        return dealerRepository.findNameById(id);
    }

    public boolean existsByDealerId(Integer dealerId) {
        return dealerRepository.existsByDealerId(dealerId);
    }

    public Optional<DealerDocument> getByDealerId(Integer dealerId) {
        return dealerRepository.findByDealerId(dealerId);
    }

    public DealerDocument save(DealerDocument dealer) {
        return dealerRepository.save(dealer);
    }

    public void deleteById(String id) {
        dealerRepository.deleteById(id);
    }

    public Optional<DealerDocument> getById(String id) {
        return dealerRepository.findById(id);
    }
}
