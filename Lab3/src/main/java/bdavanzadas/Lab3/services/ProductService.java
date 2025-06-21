package bdavanzadas.Lab3.services;

import bdavanzadas.Lab3.entities.ProductEntity;
import bdavanzadas.Lab3.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Obtener todos los productos
     */
    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    /**
     * Buscar producto por ID
     */
    public Optional<ProductEntity> findById(String id) {
        return productRepository.findById(id);
    }

    /**
     * Guardar nuevo producto
     */
    public ProductEntity save(ProductEntity product) {
        return productRepository.save(product);
    }

    /**
     * Actualizar producto existente
     */
    public ProductEntity update(ProductEntity product) {
        if (productRepository.existsById(product.getId())) {
            return productRepository.save(product);
        } else {
            throw new IllegalArgumentException("Producto con ID " + product.getId() + " no existe.");
        }
    }

    /**
     * Eliminar producto por ID
     */
    public void delete(String id) {
        productRepository.deleteById(id);
    }

    /**
     * Buscar productos con stock > 0
     */
    public List<ProductEntity> findByStock() {
        return productRepository.findByStockGreaterThan(0);
    }

    /**
     * Buscar productos por categoría
     */
    public List<ProductEntity> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    /**
     * Obtener lista de categorías únicas
     */
    public Set<String> findAllCategories() {
        List<ProductEntity> categories = productRepository.findAllCategoriesOnly();
        return categories.stream()
                .map(ProductEntity::getCategory)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    /**
     * Buscar productos por ID de compañía
     */
    public List<ProductEntity> findByCompanyId(String companyId) {
        return productRepository.findByCompanyId(companyId);
    }

    /**
     * Obtener el ID de compañía a partir del ID de producto
     */
    public String getCompanyIdByProductId(String productId) {
        return productRepository.findById(productId)
                .map(ProductEntity::getCompanyId)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con ID: " + productId));
    }
}
