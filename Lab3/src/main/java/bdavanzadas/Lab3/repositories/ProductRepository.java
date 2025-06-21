package bdavanzadas.Lab3.repositories;

import bdavanzadas.Lab3.entities.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {

    // Buscar por stock mayor a cero
    List<ProductEntity> findByStockGreaterThan(int stock);

    // Buscar por categoría
    List<ProductEntity> findByCategory(String category);

    // Obtener todas las categorías distintas
    @Query(value = "{}", fields = "{ 'category' : 1 }")
    List<ProductEntity> findAllCategoriesOnly(); // luego filtras manualmente en el servicio

    // Buscar por ID de compañía
    List<ProductEntity> findByCompanyId(String companyId);

}