package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.ClientDocument;
import bdavanzadas.lab1.documentRepositories.ClientDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientDocumentService {

    private final ClientDocumentRepository clientRepository;

    @Autowired
    public ClientDocumentService(ClientDocumentRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    //findAll
    public List<ClientDocument> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<ClientDocument> getClientByUserId(String userId) {
        return clientRepository.findByUserId(userId);
    }

    public Optional<ClientDocument> getClientByRut(String rut) {
        return clientRepository.findByRut(rut);
    }

    public String getClientNameById(String id) {
        return clientRepository.findNameById(id);
    }

    public boolean existsByClientId(Integer clientId) {
        return clientRepository.existsByClientId(clientId);
    }

    public Optional<ClientDocument> getClientByClientId(Integer clientId) {
        return clientRepository.findByClientId(clientId);
    }

    public ClientDocument saveClient(ClientDocument clientDocument) {
        return clientRepository.save(clientDocument);
    }

    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }
}