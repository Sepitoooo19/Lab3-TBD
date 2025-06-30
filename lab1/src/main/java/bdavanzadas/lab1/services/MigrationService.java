package bdavanzadas.lab1.services;

import bdavanzadas.lab1.documentRepositories.*;
import bdavanzadas.lab1.documents.*;
import bdavanzadas.lab1.entities.*;
import bdavanzadas.lab1.mappers.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para migrar datos de entidades de PostgreSQL a documentos de MongoDB.
 * Proporciona métodos para migrar diferentes tipos de entidades, asegurando
 * la integridad de los datos y evitando duplicados.
 */
@Service
public class MigrationService {

    private final ClientDocumentRepository clientDocumentRepository;
    private final DealerDocumentRepository dealerDocumentRepository;
    private final CompanyDocumentRepository companyDocumentRepository;
    private final CompanyService companyService;
    private final CoverageAreaDocumentRepository coverageAreaDocumentRepository;
    private final EmergencyReportDocumentRepository emergencyReportDocumentRepository;
    private final OrderDetailDocumentRepository orderDetailDocumentRepository;
    private final OrderDocumentRepository orderDocumentRepository;
    private final OrdersService ordersService;
    private final PaymentMethodDocumentRepository paymentMethodDocumentRepository;
    private final ProductDocumentRepository productDocumentRepository;
    private final RatingDocumentRepository ratingDocumentRepository;
    private final UserDocumentRepository userDocumentRepository;

    /**
     * Constructor que inyecta todas las dependencias necesarias.
     * @param clientDocumentRepository Repositorio de clientes en MongoDB
     * @param dealerDocumentRepository Repositorio de repartidores en MongoDB
     * @param companyService Servicio de compañías
     * @param companyDocumentRepository Repositorio de compañías en MongoDB
     * @param coverageAreaDocumentRepository Repositorio de áreas de cobertura en MongoDB
     * @param emergencyReportDocumentRepository Repositorio de reportes de emergencia en MongoDB
     * @param orderDetailDocumentRepository Repositorio de detalles de pedido en MongoDB
     * @param orderDocumentRepository Repositorio de pedidos en MongoDB
     * @param ordersService Servicio de pedidos
     * @param paymentMethodDocumentRepository Repositorio de métodos de pago en MongoDB
     * @param productDocumentRepository Repositorio de productos en MongoDB
     * @param ratingDocumentRepository Repositorio de calificaciones en MongoDB
     * @param userDocumentRepository Repositorio de usuarios en MongoDB
     */
    public MigrationService(ClientDocumentRepository clientDocumentRepository,
                            DealerDocumentRepository dealerDocumentRepository,
                            CompanyService companyService,
                            CompanyDocumentRepository companyDocumentRepository,
                            CoverageAreaDocumentRepository coverageAreaDocumentRepository,
                            EmergencyReportDocumentRepository emergencyReportDocumentRepository,
                            OrderDetailDocumentRepository orderDetailDocumentRepository,
                            OrderDocumentRepository orderDocumentRepository,
                            OrdersService ordersService,
                            PaymentMethodDocumentRepository paymentMethodDocumentRepository,
                            ProductDocumentRepository productDocumentRepository,
                            RatingDocumentRepository ratingDocumentRepository,
                            UserDocumentRepository userDocumentRepository) {
        this.clientDocumentRepository = clientDocumentRepository;
        this.dealerDocumentRepository = dealerDocumentRepository;
        this.companyService = companyService;
        this.companyDocumentRepository = companyDocumentRepository;
        this.coverageAreaDocumentRepository = coverageAreaDocumentRepository;
        this.emergencyReportDocumentRepository = emergencyReportDocumentRepository;
        this.orderDetailDocumentRepository = orderDetailDocumentRepository;
        this.orderDocumentRepository = orderDocumentRepository;
        this.ordersService = ordersService;
        this.paymentMethodDocumentRepository = paymentMethodDocumentRepository;
        this.productDocumentRepository = productDocumentRepository;
        this.ratingDocumentRepository = ratingDocumentRepository;
        this.userDocumentRepository = userDocumentRepository;
    }

    /**
     * Migra un cliente de PostgreSQL a MongoDB.
     * @param entityFromPostgres Entidad de cliente desde PostgreSQL
     */
    public void migrateClientToMongo(ClientEntity entityFromPostgres) {
        Integer clientId = entityFromPostgres.getId();

        if (clientDocumentRepository.existsByClientId(clientId)) {
            System.out.println("Documento ya existe para clientId: " + clientId);
            return;
        }

        ClientDocument document = ClientMapper.fromClientEntity(entityFromPostgres);
        clientDocumentRepository.save(document);
    }

    /**
     * Migra un repartidor de PostgreSQL a MongoDB.
     * @param entityFromPostgres Entidad de repartidor desde PostgreSQL
     */
    public void migrateDealerToMongo(DealerEntity entityFromPostgres) {
        Integer dealerId = entityFromPostgres.getId();

        if (dealerDocumentRepository.existsByDealerId(dealerId)) {
            System.out.println("Documento ya existe para dealerId: " + dealerId);
            return;
        }

        DealerDocument document = DealerMapper.fromDealerEntity(entityFromPostgres);
        dealerDocumentRepository.save(document);
    }

    /**
     * Migra una compañía de PostgreSQL a MongoDB.
     * @param company Entidad de compañía desde PostgreSQL
     */
    public void migrateCompanyToMongo(CompanyEntity company) {
        Integer companyId = company.getId();

        if (companyDocumentRepository.existsByCompanyId(companyId)) {
            System.out.println("Documento ya existe para companyId: " + companyId);
            return;
        }

        List<Integer> paymentMethodIds = companyService.getPaymentMethodIdsByCompanyId(company.getId());
        List<Integer> coverageAreaIds = companyService.getCoverageAreaIdsByCompanyId(company.getId());

        CompanyDocument document = CompanyMapper.fromCompanyEntity(company);

        document.setPaymentMethodIds(paymentMethodIds.stream()
                .map(String::valueOf)
                .collect(Collectors.toList()));

        document.setCoverageAreaIds(coverageAreaIds.stream()
                .map(String::valueOf)
                .collect(Collectors.toList()));

        companyDocumentRepository.save(document);
    }

    /**
     * Migra un área de cobertura de PostgreSQL a MongoDB.
     * @param entityFromPostgres Entidad de área de cobertura desde PostgreSQL
     */
    public void migrateCoverageAreaToMongo(CoverageAreaEntity entityFromPostgres) {
        Integer coverageAreaId = entityFromPostgres.getId();

        if (coverageAreaDocumentRepository.existsByCoverageAreaId(coverageAreaId)) {
            System.out.println("Documento ya existe para coverageAreaId: " + coverageAreaId);
            return;
        }

        CoverageAreaDocument document = CoverageAreaMapper.fromCoverageAreaEntity(entityFromPostgres);
        coverageAreaDocumentRepository.save(document);
    }

    /**
     * Migra un reporte de emergencia de PostgreSQL a MongoDB.
     * @param entityFromPostgres Entidad de reporte de emergencia desde PostgreSQL
     */
    public void migrateEmergencyReportToMongo(EmergencyReportEntity entityFromPostgres) {
        Integer emergencyReportId = entityFromPostgres.getId();

        if (emergencyReportDocumentRepository.existsByReportId(emergencyReportId)) {
            System.out.println("Documento ya existe para emergencyReportId: " + emergencyReportId);
            return;
        }

        EmergencyReportDocument document = EmergencyReportMapper.fromEmergencyReportEntity(entityFromPostgres);
        emergencyReportDocumentRepository.save(document);
    }

    /**
     * Migra un detalle de pedido de PostgreSQL a MongoDB.
     * @param entityFromPostgres Entidad de detalle de pedido desde PostgreSQL
     */
    public void migrateOrderDetailToMongo(OrderDetailsEntity entityFromPostgres) {
        Integer orderDetailId = entityFromPostgres.getId();

        if (orderDetailDocumentRepository.existsByOrderDetailId(orderDetailId)) {
            System.out.println("Documento ya existe para orderDetailId: " + orderDetailId);
            return;
        }

        OrderDetailDocument document = OrderDetailMapper.fromOrderDetailsEntity(entityFromPostgres);
        orderDetailDocumentRepository.save(document);
    }

    /**
     * Migra un pedido de PostgreSQL a MongoDB.
     * @param order Entidad de pedido desde PostgreSQL
     */
    public void migrateOrderToMongo(OrdersEntity order) {
        Integer orderId = order.getId();

        if (orderDocumentRepository.existsByOrderId(orderId)) {
            System.out.println("Ya existe OrderDocument para orderId: " + orderId);
            return;
        }

        List<Integer> productIds = ordersService.getProductIdsByOrderId(order.getId());
        OrderDocument document = OrderMapper.fromOrdersEntity(order, productIds);
        orderDocumentRepository.save(document);

        System.out.println("Migración exitosa para orderId: " + orderId);
    }

    /**
     * Migra un método de pago de PostgreSQL a MongoDB.
     * @param entityFromPostgres Entidad de método de pago desde PostgreSQL
     */
    public void migratePaymentMethodToMongo(PaymentMethodEntity entityFromPostgres) {
        Integer paymentMethodId = entityFromPostgres.getId();

        if (paymentMethodDocumentRepository.existsByPaymentMethodId(paymentMethodId)) {
            System.out.println("Documento ya existe para paymentMethodId: " + paymentMethodId);
            return;
        }

        PaymentMethodDocument document = PaymentMethodMapper.fromPaymentMethodEntity(entityFromPostgres);
        paymentMethodDocumentRepository.save(document);
    }

    /**
     * Migra un producto de PostgreSQL a MongoDB.
     * @param entityFromPostgres Entidad de producto desde PostgreSQL
     */
    public void migrateProductToMongo(ProductEntity entityFromPostgres) {
        Integer productId = entityFromPostgres.getId();

        if (productDocumentRepository.existsByProductId(productId)) {
            System.out.println("Documento ya existe para productId: " + productId);
            return;
        }

        ProductDocument document = ProductMapper.fromProductEntity(entityFromPostgres);
        productDocumentRepository.save(document);
    }

    /**
     * Migra una calificación de PostgreSQL a MongoDB.
     * @param entityFromPostgres Entidad de calificación desde PostgreSQL
     */
    public void migrateRatingToMongo(RatingEntity entityFromPostgres) {
        Integer ratingId = entityFromPostgres.getId();

        if (ratingDocumentRepository.existsByRatingId(ratingId)) {
            System.out.println("Documento ya existe para ratingId: " + ratingId);
            return;
        }

        RatingDocument document = RatingMapper.fromRatingEntity(entityFromPostgres);
        ratingDocumentRepository.save(document);
    }

    /**
     * Migra un usuario de PostgreSQL a MongoDB.
     * @param entityFromPostgres Entidad de usuario desde PostgreSQL
     */
    public void migrateUserToMongo(UserEntity entityFromPostgres) {
        Integer userId = entityFromPostgres.getId();

        if (userDocumentRepository.existsByUserId(userId)) {
            System.out.println("Documento ya existe para userId: " + userId);
            return;
        }

        UserDocument document = UserMapper.fromUserEntity(entityFromPostgres);
        userDocumentRepository.save(document);
    }
}