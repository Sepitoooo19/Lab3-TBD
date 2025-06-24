package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.UserNavigationDocument;
import bdavanzadas.lab1.documentRepositories.UserNavigationDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserNavigationDocumentService {

    private final UserNavigationDocumentRepository repository;

    @Autowired
    public UserNavigationDocumentService(UserNavigationDocumentRepository repository) {
        this.repository = repository;
    }

    public List<UserNavigationDocument> getByUserId(Integer userId) {
        return repository.findByUserId(userId);
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
}
