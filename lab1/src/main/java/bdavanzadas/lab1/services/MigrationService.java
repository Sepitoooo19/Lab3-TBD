package bdavanzadas.lab1.services;


import bdavanzadas.lab1.documentRepositories.*;
import bdavanzadas.lab1.documents.*;
import bdavanzadas.lab1.entities.*;

import bdavanzadas.lab1.mappers.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MigrationService {

    private final ClientDocumentRepository clientDocumentRepository;
    private final DealerDocumentRepository dealerDocumentRepository;
    private final CompanyDocumentRepository companyDocumentRepository;
    private final CompanyService companyService;
    private final CoverageAreaDocumentRepository coverageAreaDocumentRepository;
    private final EmergencyReportDocumentRepository emergencyReportDocumentRepository;

    public MigrationService(ClientDocumentRepository clientDocumentRepository,
                            DealerDocumentRepository dealerDocumentRepository,
                            CompanyService companyService,
                            CompanyDocumentRepository companyDocumentRepository,
                            CoverageAreaDocumentRepository coverageAreaDocumentRepository,
                            EmergencyReportDocumentRepository emergencyReportDocumentRepository) {
        this.clientDocumentRepository = clientDocumentRepository;
        this.dealerDocumentRepository = dealerDocumentRepository;
        this.companyService = companyService;
        this.companyDocumentRepository = companyDocumentRepository;
        this.coverageAreaDocumentRepository = coverageAreaDocumentRepository;
        this.emergencyReportDocumentRepository = emergencyReportDocumentRepository;

    }

    public void migrateClientToMongo(ClientEntity entityFromPostgres) {
        Integer clientId = entityFromPostgres.getId();

        if (clientDocumentRepository.existsByClientId(clientId)) {
            System.out.println("Documento ya existe para clientId: " + clientId);
            return;
        }

        ClientDocument document = ClientMapper.fromClientEntity(entityFromPostgres);
        clientDocumentRepository.save(document);
    }



    public void migrateDealerToMongo(bdavanzadas.lab1.entities.DealerEntity entityFromPostgres) {

        Integer dealerId = entityFromPostgres.getId();

        // Verificamos si ya existe un documento para este dealerId
        if (dealerDocumentRepository.existsByDealerId(dealerId)) {
            System.out.println("Documento ya existe para dealerId: " + dealerId);
            return;
        }


        // Si no existe, lo migramos
        bdavanzadas.lab1.documents.DealerDocument document = DealerMapper.fromDealerEntity(entityFromPostgres);
        dealerDocumentRepository.save(document);
    }

    public void migrateCompanyToMongo(CompanyEntity company) {

        Integer companyId = company.getId();
        // Verificamos si ya existe un documento para este companyId
        if (companyDocumentRepository.existsByCompanyId(companyId)) {
            System.out.println("Documento ya existe para companyId: " + companyId);
            return;
        }
        // Si no existe, lo migramos
        // Primero, obtenemos los IDs de métodos de pago y áreas de cobertura


        // Obtén los IDs relacionados
        List<Integer> paymentMethodIds = companyService.getPaymentMethodIdsByCompanyId(company.getId());
        List<Integer> coverageAreaIds = companyService.getCoverageAreaIdsByCompanyId(company.getId());

        CompanyDocument document = CompanyMapper.fromCompanyEntity(company);

        // Convierte y asigna las listas de IDs como strings (Mongo espera listas de strings)
        document.setPaymentMethodIds(paymentMethodIds.stream()
                .map(String::valueOf)
                .collect(Collectors.toList()));

        document.setCoverageAreaIds(coverageAreaIds.stream()
                .map(String::valueOf)
                .collect(Collectors.toList()));

        companyDocumentRepository.save(document);
    }

    public void migrateCoverageAreaToMongo(CoverageAreaEntity entityFromPostgres) {
        Integer coverageAreaId = entityFromPostgres.getId();

        // Verificamos si ya existe un documento para este coverageAreaId
        if (coverageAreaDocumentRepository.existsByCoverageAreaId(coverageAreaId)) {
            System.out.println("Documento ya existe para coverageAreaId: " + coverageAreaId);
            return;
        }

        CoverageAreaDocument document = CoverageAreaMapper.fromCoverageAreaEntity(entityFromPostgres);
        coverageAreaDocumentRepository.save(document);
    }

    public void migrateEmergencyReportToMongo(EmergencyReportEntity entityFromPostgres) {
        Integer emergencyReportId = entityFromPostgres.getId();

        // Verificamos si ya existe un documento para este emergencyReportId
        if (emergencyReportDocumentRepository.existsByReportId(emergencyReportId)) {
            System.out.println("Documento ya existe para emergencyReportId: " + emergencyReportId);
            return;
        }

        EmergencyReportDocument document = EmergencyReportMapper.fromEmergencyReportEntity(entityFromPostgres);
        emergencyReportDocumentRepository.save(document);
    }



}