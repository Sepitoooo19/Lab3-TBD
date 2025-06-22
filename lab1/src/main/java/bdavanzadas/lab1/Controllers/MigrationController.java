package bdavanzadas.lab1.Controllers;

import bdavanzadas.lab1.entities.ClientEntity;
import bdavanzadas.lab1.entities.DealerEntity;
import bdavanzadas.lab1.services.ClientService;
import bdavanzadas.lab1.services.CompanyService;
import bdavanzadas.lab1.services.*;
import bdavanzadas.lab1.services.MigrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/migration")
public class MigrationController {

    private final ClientService clientService;
    private final DealerService dealerService;
    private final CompanyService companyService;
    private final MigrationService migrationService;
    private final CoverageAreaService coverageAreaService;

    public MigrationController(ClientService clientService,
                               MigrationService migrationService,
                               DealerService dealerService,
                               CompanyService companyService,
                               CoverageAreaService coverageAreaService) {
        this.clientService = clientService;
        this.migrationService = migrationService;
        this.dealerService = dealerService;
        this.companyService = companyService;
        this.coverageAreaService = coverageAreaService;

    }

    @PostMapping("/clients")
    public ResponseEntity<String> migrateClientsToMongo() {
        List<ClientEntity> postgresClients = clientService.getAllClients();

        for (ClientEntity client : postgresClients) {
            migrationService.migrateClientToMongo(client);
        }

        return ResponseEntity.ok("Clientes migrados a MongoDB");
    }

    @PostMapping("/dealers")
    public ResponseEntity<String> migrateDealersToMongo() {
        List<DealerEntity> postgresDealers = dealerService.getAllDealers();

        for (DealerEntity dealer : postgresDealers) {
            migrationService.migrateDealerToMongo(dealer);
        }

        return ResponseEntity.ok("Concesionarios migrados a MongoDB");
    }

    @PostMapping("/companies")
    public ResponseEntity<String> migrateCompaniesToMongo() {
        List<bdavanzadas.lab1.entities.CompanyEntity> postgresCompanies = companyService.getAllCompanies();

        for (bdavanzadas.lab1.entities.CompanyEntity company : postgresCompanies) {
            migrationService.migrateCompanyToMongo(company);
        }

        return ResponseEntity.ok("Empresas migradas a MongoDB");
    }

    @PostMapping("/coverage-areas")
    public ResponseEntity<String> migrateCoverageAreasToMongo() {
        List<bdavanzadas.lab1.entities.CoverageAreaEntity> postgresCoverageAreas = coverageAreaService.getAllCoverageAreas();

        for (bdavanzadas.lab1.entities.CoverageAreaEntity coverageArea : postgresCoverageAreas) {
            migrationService.migrateCoverageAreaToMongo(coverageArea);
        }

        return ResponseEntity.ok("Áreas de cobertura migradas a MongoDB");
    }
}
