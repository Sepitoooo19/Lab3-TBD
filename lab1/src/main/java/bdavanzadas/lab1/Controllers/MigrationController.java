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


/**
 *
 * La clase MigrationController maneja las solicitudes de migración de datos desde PostgreSQL a MongoDB.
 * Esta clase contiene métodos para migrar diferentes entidades como clientes, concesionarios, empresas, áreas de cobertura, informes de emergencia, detalles de pedidos, pedidos, métodos de pago, productos, calificaciones y usuarios.
 * * Cada método realiza una migración de una entidad específica desde la base de datos PostgreSQL a MongoDB.
 *
 * */
@RestController
@RequestMapping("/migration")
public class MigrationController {


    /**
     *
     *
     * Servicios utilizados para la migración de datos.
     * Estos servicios interactúan con las bases de datos PostgreSQL y MongoDB para realizar la migración de las diferentes entidades.
     * */
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


    /**
     * Constructor de la clase MigrationController.
     * Este constructor inicializa los servicios necesarios para la migración de datos.
     *
     * @param clientService Servicio de clientes
     * @param migrationService Servicio de migración
     * @param dealerService Servicio de concesionarios
     * @param companyService Servicio de empresas
     * @param coverageAreaService Servicio de áreas de cobertura
     * @param emergencyReportService Servicio de informes de emergencia
     * @param orderDetailsService Servicio de detalles de pedidos
     * @param ordersService Servicio de pedidos
     * @param paymentMethodService Servicio de métodos de pago
     * @param productService Servicio de productos
     * @param ratingService Servicio de calificaciones
     * @param userService Servicio de usuarios
     */
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


    /**
     * Endpoint para migrar clientes desde PostgreSQL a MongoDB.
     * Este endpoint obtiene todos los clientes de la base de datos PostgreSQL y los migra a MongoDB.
     *
     * @return Respuesta con el estado de la migración
     */
    @PostMapping("/clients")
    public ResponseEntity<String> migrateClientsToMongo() {
        List<ClientEntity> postgresClients = clientService.getAllClients();

        for (ClientEntity client : postgresClients) {
            migrationService.migrateClientToMongo(client);
        }

        return ResponseEntity.ok("Clientes migrados a MongoDB");
    }


    /**
     * Endpoint para migrar concesionarios desde PostgreSQL a MongoDB.
     * Este endpoint obtiene todos los concesionarios de la base de datos PostgreSQL y los migra a MongoDB.
     *
     * @return Respuesta con el estado de la migración
     */
    @PostMapping("/dealers")
    public ResponseEntity<String> migrateDealersToMongo() {
        List<DealerEntity> postgresDealers = dealerService.getAllDealers();

        for (DealerEntity dealer : postgresDealers) {
            migrationService.migrateDealerToMongo(dealer);
        }

        return ResponseEntity.ok("Concesionarios migrados a MongoDB");
    }


    /**
     * Endpoint para migrar empresas desde PostgreSQL a MongoDB.
     * Este endpoint obtiene todas las empresas de la base de datos PostgreSQL y las migra a MongoDB.
     *
     * @return Respuesta con el estado de la migración
     */
    @PostMapping("/companies")
    public ResponseEntity<String> migrateCompaniesToMongo() {
        List<CompanyEntity> postgresCompanies = companyService.getAllCompanies();

        for (CompanyEntity company : postgresCompanies) {
            migrationService.migrateCompanyToMongo(company);
        }

        return ResponseEntity.ok("Empresas migradas a MongoDB");
    }


    /**
     * Endpoint para migrar áreas de cobertura desde PostgreSQL a MongoDB.
     * Este endpoint obtiene todas las áreas de cobertura de la base de datos PostgreSQL y las migra a MongoDB.
     *
     * @return Respuesta con el estado de la migración
     */
    @PostMapping("/coverage-areas")
    public ResponseEntity<String> migrateCoverageAreasToMongo() {
        List<CoverageAreaEntity> postgresCoverageAreas = coverageAreaService.getAllCoverageAreas();

        for (CoverageAreaEntity coverageArea : postgresCoverageAreas) {
            migrationService.migrateCoverageAreaToMongo(coverageArea);
        }

        return ResponseEntity.ok("Áreas de cobertura migradas a MongoDB");
    }


    /**
     * Endpoint para migrar informes de emergencia desde PostgreSQL a MongoDB.
     * Este endpoint obtiene todos los informes de emergencia de la base de datos PostgreSQL y los migra a MongoDB.
     *
     * @return Respuesta con el estado de la migración
     */
    @PostMapping("/emergency-reports")
    public ResponseEntity<String> migrateEmergencyReportsToMongo() {
        List<EmergencyReportEntity> postgresEmergencyReports = emergencyReportService.getAllEmergencyReports();

        for (EmergencyReportEntity emergencyReport : postgresEmergencyReports) {
            migrationService.migrateEmergencyReportToMongo(emergencyReport);
        }

        return ResponseEntity.ok("Informes de emergencia migrados a MongoDB");
    }


    /**
     * Endpoint para migrar detalles de pedidos desde PostgreSQL a MongoDB.
     * Este endpoint obtiene todos los detalles de pedidos de la base de datos PostgreSQL y los migra a MongoDB.
     *
     * @return Respuesta con el estado de la migración
     */
    @PostMapping("/order-details")
    public ResponseEntity<String> migrateOrderDetailsToMongo() {
        List<OrderDetailsEntity> postgresOrderDetails = orderDetailsService.getAllOrderDetails();

        for (OrderDetailsEntity orderDetails : postgresOrderDetails) {
            migrationService.migrateOrderDetailToMongo(orderDetails);
        }

        return ResponseEntity.ok("Detalles de pedidos migrados a MongoDB");
    }


    /**
     * Endpoint para migrar pedidos desde PostgreSQL a MongoDB.
     * Este endpoint obtiene todos los pedidos de la base de datos PostgreSQL y los migra a MongoDB.
     *
     * @return Respuesta con el estado de la migración
     */
    @PostMapping("/order")
    public ResponseEntity<String> migrateOrdersToMongo() {
        List<OrdersEntity> postgresOrders = ordersService.getAllOrders();

        for (OrdersEntity order : postgresOrders) {
            migrationService.migrateOrderToMongo(order);
        }

        return ResponseEntity.ok("Pedidos migrados a MongoDB");
    }



    /**
     * Endpoint para migrar métodos de pago desde PostgreSQL a MongoDB.
     * Este endpoint obtiene todos los métodos de pago de la base de datos PostgreSQL y los migra a MongoDB.
     *
     * @return Respuesta con el estado de la migración
     */
    @PostMapping("/payment-methods")
    public ResponseEntity<String> migratePaymentMethodsToMongo() {
        List<PaymentMethodEntity> postgresPaymentMethods = paymentMethodService.getallPaymentMethods();

        for (PaymentMethodEntity paymentMethod : postgresPaymentMethods) {
            migrationService.migratePaymentMethodToMongo(paymentMethod);
        }

        return ResponseEntity.ok("Métodos de pago migrados a MongoDB");
    }


    /**
     * Endpoint para migrar productos desde PostgreSQL a MongoDB.
     * Este endpoint obtiene todos los productos de la base de datos PostgreSQL y los migra a MongoDB.
     *
     * @return Respuesta con el estado de la migración
     */
    @PostMapping("/products")
    public ResponseEntity<String> migrateProductsToMongo() {
        List<ProductEntity> postgresProducts = productService.getAllProducts();

        for (ProductEntity product : postgresProducts) {
            migrationService.migrateProductToMongo(product);
        }

        return ResponseEntity.ok("Productos migrados a MongoDB");
    }


    /**
     * Endpoint para migrar calificaciones desde PostgreSQL a MongoDB.
     * Este endpoint obtiene todas las calificaciones de la base de datos PostgreSQL y las migra a MongoDB.
     *
     * @return Respuesta con el estado de la migración
     */
    @PostMapping("/ratings")
    public ResponseEntity<String> migrateRatingsToMongo() {
        List<RatingEntity> postgresRatings = ratingService.getAllRatings();

        for (RatingEntity rating : postgresRatings) {
            migrationService.migrateRatingToMongo(rating);
        }

        return ResponseEntity.ok("Calificaciones migradas a MongoDB");
    }


    /**
     * Endpoint para migrar usuarios desde PostgreSQL a MongoDB.
     * Este endpoint obtiene todos los usuarios de la base de datos PostgreSQL y los migra a MongoDB.
     *
     * @return Respuesta con el estado de la migración
     */
    @PostMapping("/users")
    public ResponseEntity<String> migrateUsersToMongo() {
        List<UserEntity> postgresUsers = userService.getAllUsers();

        for (UserEntity user : postgresUsers) {
            migrationService.migrateUserToMongo(user);
        }

        return ResponseEntity.ok("Usuarios migrados a MongoDB");
    }


}
