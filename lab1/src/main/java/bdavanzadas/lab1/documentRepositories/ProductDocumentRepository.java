package bdavanzadas.lab1.documentRepositories;

import bdavanzadas.lab1.documents.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones con documentos de productos en MongoDB.
 * Proporciona métodos para consultar y gestionar productos del sistema,
 * incluyendo búsquedas por disponibilidad, categoría y compañía asociada.
 */
@Repository
public interface ProductDocumentRepository extends MongoRepository<ProductDocument, String> {

    /**
     * Busca productos con stock superior al valor especificado.
     * @param stock Valor mínimo de stock para filtrar (exclusivo).
     * @return Lista de productos con stock mayor al valor especificado.
     *         Retorna lista vacía si no hay productos que cumplan el criterio.
     */
    List<ProductDocument> findByStockGreaterThan(int stock);

    /**
     * Busca productos por categoría específica.
     * @param category Nombre de la categoría a filtrar (case-sensitive).
     * @return Lista de productos pertenecientes a la categoría especificada.
     *         Retorna lista vacía si no hay productos en la categoría.
     */
    List<ProductDocument> findByCategory(String category);

    /**
     * Obtiene todos los documentos de productos proyectando solo el campo categoría.
     * @return Lista de documentos de productos conteniendo solo el campo categoría.
     * @apiNote Este método requiere procesamiento adicional en el servicio para
     *          extraer las categorías únicas. Considerar usar una agregación
     *          MongoDB para obtener directamente las categorías distintas.
     */
    @Query(value = "{}", fields = "{ 'category' : 1 }")
    List<ProductDocument> findAllCategoriesOnly();

    /**
     * Busca productos asociados a una compañía específica.
     * @param companyId ID de la compañía cuyos productos se desean consultar.
     * @return Lista de productos asociados a la compañía especificada.
     *         Retorna lista vacía si la compañía no tiene productos asociados.
     */
    List<ProductDocument> findByCompanyId(String companyId);

    /**
     * Verifica si existe un producto con el ID numérico especificado.
     * @param productId ID numérico del producto a verificar.
     * @return true si existe un producto con ese ID, false en caso contrario.
     */
    boolean existsByProductId(Integer productId);

    /**
     * Busca un producto por su ID numérico único.
     * @param productId ID numérico del producto a buscar.
     * @return Optional que contiene el producto si existe.
     */
    Optional<ProductDocument> findByProductId(Integer productId);
}