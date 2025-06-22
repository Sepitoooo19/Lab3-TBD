package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductDocumentRepository extends MongoRepository<ProductDocument, String> {

    // Buscar por stock mayor a cero
    List<ProductDocument> findByStockGreaterThan(int stock);

    // Buscar por categoría
    List<ProductDocument> findByCategory(String category);

    // Obtener todas las categorías distintas
    @Query(value = "{}", fields = "{ 'category' : 1 }")
    List<ProductDocument> findAllCategoriesOnly(); // luego filtras manualmente en el servicio

    // Buscar por ID de compañía
    List<ProductDocument> findByCompanyId(String companyId);

    boolean existsByProductId(Integer productId);
    Optional<ProductDocument> findByProductId(Integer productId);

}