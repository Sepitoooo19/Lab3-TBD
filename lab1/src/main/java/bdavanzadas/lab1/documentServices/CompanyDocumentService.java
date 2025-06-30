package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documentRepositories.OrderDocumentRepository;
import bdavanzadas.lab1.documentRepositories.ProductDocumentRepository;
import bdavanzadas.lab1.documents.CompanyDocument;
import bdavanzadas.lab1.documentRepositories.CompanyDocumentRepository;
import bdavanzadas.lab1.documents.OrderDocument;
import bdavanzadas.lab1.documents.ProductDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar operaciones con documentos de compañías.
 * Proporciona métodos para buscar, crear, actualizar y eliminar compañías,
 * así como para consultar información específica sobre ellas y sus relaciones
 * con otros documentos como pedidos y productos.
 */
@Service
public class CompanyDocumentService {

    private final CompanyDocumentRepository companyRepository;
    private final OrderDocumentRepository orderRepository;
    private final ProductDocumentRepository productRepository;

    /**
     * Constructor que inyecta los repositorios necesarios.
     * @param companyRepository Repositorio de compañías
     * @param orderRepository Repositorio de pedidos
     * @param productRepository Repositorio de productos
     */
    @Autowired
    public CompanyDocumentService(CompanyDocumentRepository companyRepository,
                                  OrderDocumentRepository orderRepository,
                                  ProductDocumentRepository productRepository) {
        this.companyRepository = companyRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    /**
     * Busca una compañía por su RUT único.
     * @param rut RUT de la compañía a buscar (formato: 12345678-9)
     * @return Optional que contiene la compañía si existe
     */
    public Optional<CompanyDocument> getByRut(String rut) {
        return companyRepository.findByRut(rut);
    }

    /**
     * Busca compañías por su tipo.
     * @param type Tipo de compañía (ej: "Retail", "Farmacia", "Distribuidor")
     * @return Lista de compañías del tipo especificado
     */
    public List<CompanyDocument> getByType(String type) {
        return companyRepository.findByType(type);
    }

    /**
     * Busca compañías que aceptan un método de pago específico.
     * @param paymentMethodId ID del método de pago
     * @return Lista de compañías que aceptan el método de pago
     */
    public List<CompanyDocument> getByPaymentMethodId(String paymentMethodId) {
        return companyRepository.findByPaymentMethodId(paymentMethodId);
    }

    /**
     * Busca compañías que operan en un área de cobertura específica.
     * @param coverageAreaId ID del área de cobertura
     * @return Lista de compañías que cubren el área especificada
     */
    public List<CompanyDocument> getByCoverageAreaId(String coverageAreaId) {
        return companyRepository.findByCoverageAreaId(coverageAreaId);
    }

    /**
     * Verifica si existe una compañía con el ID especificado.
     * @param companyId ID numérico de la compañía
     * @return true si existe, false en caso contrario
     */
    public boolean existsByCompanyId(Integer companyId) {
        return companyRepository.existsByCompanyId(companyId);
    }



    /**
     * Guarda o actualiza una compañía.
     * @param company Documento de la compañía a guardar
     * @return Compañía guardada/actualizada
     */
    public CompanyDocument save(CompanyDocument company) {
        return companyRepository.save(company);
    }

    /**
     * Elimina una compañía por su ID de documento.
     * @param id ID del documento de la compañía
     */
    public void deleteById(String id) {
        companyRepository.deleteById(id);
    }

    /**
     * Busca una compañía por su ID de documento.
     * @param id ID del documento de la compañía
     * @return Optional que contiene la compañía si existe
     */
    public Optional<CompanyDocument> getById(String id) {
        return companyRepository.findById(id);
    }

    /**
     * Obtiene todas las compañías registradas.
     * @return Lista de todas las compañías
     */
    public List<CompanyDocument> getAll() {
        return companyRepository.findAll();
    }
}