package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.UserNavigationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserNavigationDocumentRepository extends MongoRepository<UserNavigationDocument, String> {
    List<UserNavigationDocument> findByUserId(Integer userId);
}
