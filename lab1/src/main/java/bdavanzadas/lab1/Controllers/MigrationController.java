package bdavanzadas.lab1.Controllers;

import bdavanzadas.lab1.entities.ClientEntity;
import bdavanzadas.lab1.entities.*;
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
    private final EmergencyReportService emergencyReportService;
    private final OrderDetailsService orderDetailsService;
    private final OrdersService ordersService;
    private final PaymentMethodService paymentMethodService;
    private final ProductService productService;
    private final RatingService ratingService;
    private final UserService userService;

    public MigrationController(ClientService clientService,
                               MigrationService migrationService,
                               DealerService dealerService,
                               CompanyService companyService,
                               CoverageAreaService coverageAreaService,
                               EmergencyReportService emergencyReportService,
                               OrderDetailsService orderDetailsService,
                               OrdersService ordersService,
                               PaymentMethodService paymentMethodService,
                               ProductService productService,
                               RatingService ratingService,
                               UserService userService) {
        this.clientService = clientService;
        this.migrationService = migrationService;
        this.dealerService = dealerService;
        this.companyService = companyService;
        this.coverageAreaService = coverageAreaService;
        this.emergencyReportService = emergencyReportService;
        this.orderDetailsService = orderDetailsService;
        this.ordersService = ordersService;
        this.paymentMethodService = paymentMethodService;
        this.productService = productService;
        this.ratingService = ratingService;
        this.userService = userService;

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
        List<CompanyEntity> postgresCompanies = companyService.getAllCompanies();

        for (CompanyEntity company : postgresCompanies) {
            migrationService.migrateCompanyToMongo(company);
        }

        return ResponseEntity.ok("Empresas migradas a MongoDB");
    }

    @PostMapping("/coverage-areas")
    public ResponseEntity<String> migrateCoverageAreasToMongo() {
        List<CoverageAreaEntity> postgresCoverageAreas = coverageAreaService.getAllCoverageAreas();

        for (CoverageAreaEntity coverageArea : postgresCoverageAreas) {
            migrationService.migrateCoverageAreaToMongo(coverageArea);
        }

        return ResponseEntity.ok("Áreas de cobertura migradas a MongoDB");
    }

    @PostMapping("/emergency-reports")
    public ResponseEntity<String> migrateEmergencyReportsToMongo() {
        List<EmergencyReportEntity> postgresEmergencyReports = emergencyReportService.getAllEmergencyReports();

        for (EmergencyReportEntity emergencyReport : postgresEmergencyReports) {
            migrationService.migrateEmergencyReportToMongo(emergencyReport);
        }

        return ResponseEntity.ok("Informes de emergencia migrados a MongoDB");
    }

    @PostMapping("/order-details")
    public ResponseEntity<String> migrateOrderDetailsToMongo() {
        List<OrderDetailsEntity> postgresOrderDetails = orderDetailsService.getAllOrderDetails();

        for (OrderDetailsEntity orderDetails : postgresOrderDetails) {
            migrationService.migrateOrderDetailToMongo(orderDetails);
        }

        return ResponseEntity.ok("Detalles de pedidos migrados a MongoDB");
    }
    //migrateOrderToMongo
    @PostMapping("/order")
    public ResponseEntity<String> migrateOrdersToMongo() {
        List<OrdersEntity> postgresOrders = ordersService.getAllOrders();

        for (OrdersEntity order : postgresOrders) {
            migrationService.migrateOrderToMongo(order);
        }

        return ResponseEntity.ok("Pedidos migrados a MongoDB");
    }

    //migratePaymentMethodToMongo
    @PostMapping("/payment-methods")
    public ResponseEntity<String> migratePaymentMethodsToMongo() {
        List<PaymentMethodEntity> postgresPaymentMethods = paymentMethodService.getallPaymentMethods();

        for (PaymentMethodEntity paymentMethod : postgresPaymentMethods) {
            migrationService.migratePaymentMethodToMongo(paymentMethod);
        }

        return ResponseEntity.ok("Métodos de pago migrados a MongoDB");
    }
}
