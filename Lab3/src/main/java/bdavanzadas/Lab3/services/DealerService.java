package bdavanzadas.Lab3.services;

import bdavanzadas.Lab3.entities.DealerEntity;
import bdavanzadas.Lab3.repositories.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealerService {

    private final DealerRepository dealerRepository;

    @Autowired
    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public DealerEntity createDealer(DealerEntity dealer) {
        return dealerRepository.save(dealer);
    }

    public Optional<DealerEntity> getDealerById(String id) {
        return dealerRepository.findById(id);
    }

    public Optional<DealerEntity> getDealerByUserId(String userId) {
        return dealerRepository.findByUserId(userId);
    }

    public Optional<DealerEntity> getDealerByRut(String rut) {
        return dealerRepository.findByRut(rut);
    }

    //findall

    public List<DealerEntity> getAllDealers() {
        return dealerRepository.findAll();
    }

    //delete
    public void deleteDealer(String id) {
        dealerRepository.deleteById(id);
    }
    //update
    public DealerEntity updateDealer(String id, DealerEntity dealerDetails) {
        return dealerRepository.findById(id)
                .map(existingDealer -> {
                    existingDealer.setName(dealerDetails.getName());
                    existingDealer.setRut(dealerDetails.getRut());
                    existingDealer.setEmail(dealerDetails.getEmail());
                    existingDealer.setPhone(dealerDetails.getPhone());
                    existingDealer.setVehicle(dealerDetails.getVehicle());
                    existingDealer.setPlate(dealerDetails.getPlate());
                    existingDealer.setLocation(dealerDetails.getLocation());
                    return dealerRepository.save(existingDealer);
                })
                .orElseThrow(() -> new RuntimeException("Repartidor no encontrado con ID: " + id));
    }

    //findNameById
    public String getDealerNameById(String id) {
        return dealerRepository.findNameById(id);
    }




}
