package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserDocumentRepository extends MongoRepository<UserDocument, String> {
    Optional<UserDocument> findByUsername(String username);
}