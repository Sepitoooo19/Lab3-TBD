package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.UserNavigationDocument;
import bdavanzadas.lab1.documentRepositories.UserNavigationDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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


    public List<UserNavigationDocument> getSearchesWithoutOrders() {
        // 1. Obtener todos los clientes que hicieron búsquedas
        List<Integer> searchClients = repository.findDistinctClientIdsByEventType("search");

        // 2. Obtener todos los clientes que confirmaron pedidos
        List<Integer> orderClients = repository.findDistinctClientIdsByEventType("order_confirmed");

        // 3. Filtrar clientes con búsquedas pero sin pedidos
        List<Integer> targetClients = searchClients.stream()
                .filter(clientId -> !orderClients.contains(clientId))
                .collect(Collectors.toList());

        // 4. Obtener todos los documentos de búsqueda de estos clientes
        return targetClients.stream()
                .flatMap(clientId -> repository.findByClientIdAndEventType(clientId, "search").stream())
                .collect(Collectors.toList());
    }
}

