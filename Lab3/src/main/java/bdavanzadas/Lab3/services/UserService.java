package bdavanzadas.Lab3.services;

import bdavanzadas.Lab3.entities.ClientEntity;
import bdavanzadas.Lab3.entities.DealerEntity;
import bdavanzadas.Lab3.entities.UserEntity;
import bdavanzadas.Lab3.repositories.ClientRepository;
import bdavanzadas.Lab3.repositories.DealerRepository;
import bdavanzadas.Lab3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final ClientRepository clientRepository;
    private final DealerRepository dealerRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder,
                       ClientRepository clientRepository, DealerRepository dealerRepository) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.clientRepository = clientRepository;
        this.dealerRepository = dealerRepository;
    }

    public Optional<UserEntity> validateCredentials(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> encoder.matches(password, user.getPassword()));
    }

    public String getAuthenticatedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof String) {
            return (String) auth.getPrincipal();
        }
        throw new RuntimeException("Usuario no autenticado");
    }

    public UserEntity registerAdmin(String username, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole("ADMIN");
        return userRepository.save(user);
    }

    public UserEntity registerClient(String username, String password, String name, String rut,
                               String email, String phone, String address, double longitude, double latitude) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole("CLIENT");
        UserEntity savedUser = userRepository.save(user);

        ClientEntity client = new ClientEntity();
        client.setName(name);
        client.setRut(rut);
        client.setEmail(email);
        client.setPhone(phone);
        client.setAddress(address);
        client.setLocation(new GeoJsonPoint(longitude, latitude));
        client.setUserId(savedUser.getId());
        clientRepository.save(client);

        return savedUser;
    }

    public UserEntity registerDealer(String username, String password, String name, String rut,
                               String email, String phone, String vehicle, String plate,
                               double longitude, double latitude) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole("DEALER");
        UserEntity savedUser = userRepository.save(user);

        DealerEntity dealer = new DealerEntity();
        dealer.setName(name);
        dealer.setRut(rut);
        dealer.setEmail(email);
        dealer.setPhone(phone);
        dealer.setVehicle(vehicle);
        dealer.setPlate(plate);
        dealer.setLocation(new GeoJsonPoint(longitude, latitude));
        dealer.setUserId(savedUser.getId());
        dealerRepository.save(dealer);


        return savedUser;
    }
}
