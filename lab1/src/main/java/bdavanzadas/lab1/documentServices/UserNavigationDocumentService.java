package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.UserNavigationDocument;
import bdavanzadas.lab1.documentRepositories.UserNavigationDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bdavanzadas.lab1.projections.SearchWithoutOrderProjection;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserNavigationDocumentService {

    private final UserNavigationDocumentRepository repository;

    @Autowired
    public UserNavigationDocumentService(UserNavigationDocumentRepository repository) {
        this.repository = repository;
    }

    public List<UserNavigationDocument> getByClientId(Integer clientId) {
        return repository.findByClientId(clientId);
    }

    public List<UserNavigationDocument> getAll() {
        return repository.findAll();
    }

    public UserNavigationDocument save(UserNavigationDocument doc) {
        return repository.save(doc);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }


    public List<UserNavigationDocument> getNonOrderNavigationDocuments() {
        List<String> excludedTypes = Arrays.asList("order_confirmed", "order_failed");
        return repository.findByEventTypeNotIn(excludedTypes);
    }
}
