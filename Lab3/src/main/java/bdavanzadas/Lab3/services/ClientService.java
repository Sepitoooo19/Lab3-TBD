package bdavanzadas.Lab3.services;

import bdavanzadas.Lab3.entities.ClientEntity;
import bdavanzadas.Lab3.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Crear o actualizar cliente
    public ClientEntity saveClient(ClientEntity client) {
        return clientRepository.save(client);
    }

    // Obtener cliente por ID
    public Optional<ClientEntity> getClientById(String id) {
        return clientRepository.findById(id);
    }

    // Obtener cliente por ID de usuario
    public Optional<ClientEntity> getClientByUserId(String userId) {
        return clientRepository.findByUserId(userId);
    }

    // Obtener todos los clientes
    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    // Eliminar cliente
    public void deleteClient(String id) {
        clientRepository.deleteById(id);
    }

    // Obtener nombre por ID
    public String getClientNameById(String id) {
        return clientRepository.findNameById(id);
    }



    // Actualizar datos de cliente
    public ClientEntity updateClient(String id, ClientEntity clientDetails) {
        return clientRepository.findById(id)
                .map(existingClient -> {
                    existingClient.setName(clientDetails.getName());
                    existingClient.setRut(clientDetails.getRut());
                    existingClient.setEmail(clientDetails.getEmail());
                    existingClient.setPhone(clientDetails.getPhone());
                    existingClient.setAddress(clientDetails.getAddress());
                    existingClient.setLocation(clientDetails.getLocation());
                    return clientRepository.save(existingClient);
                })
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }
}