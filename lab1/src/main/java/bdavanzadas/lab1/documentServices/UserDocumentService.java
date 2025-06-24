package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.UserDocument;
import bdavanzadas.lab1.documentRepositories.UserDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDocumentService {

    private final UserDocumentRepository userRepository;

    @Autowired
    public UserDocumentService(UserDocumentRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDocument> getAll() {
        return userRepository.findAll();
    }

    public Optional<UserDocument> getById(String id) {
        return userRepository.findById(id);
    }

    public Optional<UserDocument> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existsByUserId(Integer userId) {
        return userRepository.existsByUserId(userId);
    }

    public Optional<UserDocument> getByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    public UserDocument save(UserDocument user) {
        return userRepository.save(user);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
