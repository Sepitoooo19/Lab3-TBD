package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.ProductDocument;
import bdavanzadas.lab1.documentRepositories.ProductDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Servicio para gestionar operaciones con productos.
 * Proporciona métodos para buscar, crear, actualizar y eliminar
 * productos del sistema.
 */
@Service
public class ProductDocumentService {


    /**
     * Repositorio para acceder a los documentos de productos.
     * Este repositorio permite realizar operaciones CRUD sobre los productos.
     */
    private final ProductDocumentRepository productRepository;


    /**
     * Constructor que inyecta el repositorio de productos.
     * @param productRepository Repositorio de productos a utilizar
     */
    @Autowired
    public ProductDocumentService(ProductDocumentRepository productRepository) {
        this.productRepository = productRepository;
    }


    /**
     * Obtiene todos los productos registrados en el sistema.
     * @return Lista de todos los documentos de productos.
     *         Retorna lista vacía si no hay productos registrados.
     */
    public List<ProductDocument> getAll() {
        return productRepository.findAll();
    }


    /**
     * Obtiene un producto por su ID.
     * @param id ID del producto
     * @return Documento del producto si existe, Optional vacío en caso contrario
     */
    public Optional<ProductDocument> getById(String id) {
        return productRepository.findById(id);
    }


    /**
     * Obtiene todos los productos que tienen un stock mayor al valor especificado.
     * @param stock Valor mínimo de stock
     * @return Lista de productos con stock mayor al valor especificado
     */
    public List<ProductDocument> getByStockGreaterThan(int stock) {
        return productRepository.findByStockGreaterThan(stock);
    }


    /**
     * Obtiene todos los productos que pertenecen a una categoría específica.
     * @param category Categoría del producto
     * @return Lista de productos que pertenecen a la categoría especificada
     */
    public List<ProductDocument> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }


    /**
     * Obtiene todas las categorías distintas de productos registradas en el sistema.
     * @return Lista de categorías de productos distintas.
     *
     */
    public List<String> getAllDistinctCategories() {
        return productRepository.findAllCategoriesOnly().stream()
                .map(ProductDocument::getCategory)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los productos asociados a una empresa por su ID.
     * @param companyId ID de la empresa
     * @return Lista de documentos de productos asociados a la empresa
     */
    public List<ProductDocument> getByCompanyId(String companyId) {
        return productRepository.findByCompanyId(companyId);
    }

    /**
     * Verifica si existe un producto con el ID de producto especificado.
     * @param productId ID del producto
     * @return true si existe, false en caso contrario
     */
    public boolean existsByProductId(Integer productId) {
        return productRepository.existsByProductId(productId);
    }

    /**
     * Busca un producto específico por su ID de producto.
     * @param productId ID del producto
     * @return Optional con el documento del producto si existe
     */
    public Optional<ProductDocument> getByProductId(Integer productId) {
        return productRepository.findByProductId(productId);
    }

    /**
     * Guarda o actualiza un producto.
     * @param product Documento del producto a guardar o actualizar
     * @return Producto guardado/actualizado
     */
    public ProductDocument save(ProductDocument product) {
        return productRepository.save(product);
    }


    /**
     * Elimina un producto por su ID de documento.
     * @param id ID del documento del producto a eliminar
     */
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
