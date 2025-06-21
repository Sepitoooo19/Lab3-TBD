package bdavanzadas.Lab3.controllers;

import bdavanzadas.Lab3.entities.ProductEntity;
import bdavanzadas.Lab3.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable String id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo producto
    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        return ResponseEntity.ok(productService.save(product));
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable String id, @RequestBody ProductEntity product) {
        product.setId(id);
        return ResponseEntity.ok(productService.update(product));
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener productos con stock > 0
    @GetMapping("/in-stock")
    public ResponseEntity<List<ProductEntity>> getProductsInStock() {
        return ResponseEntity.ok(productService.findByStock());
    }

    // Obtener productos por categoría
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductEntity>> getProductsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.findByCategory(category));
    }

    // Obtener todas las categorías únicas
    @GetMapping("/categories")
    public ResponseEntity<Set<String>> getAllCategories() {
        return ResponseEntity.ok(productService.findAllCategories());
    }

    // Obtener productos por ID de compañía
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ProductEntity>> getProductsByCompanyId(@PathVariable String companyId) {
        return ResponseEntity.ok(productService.findByCompanyId(companyId));
    }

    // Obtener companyId por productId
    @GetMapping("/{productId}/company-id")
    public ResponseEntity<String> getCompanyIdByProductId(@PathVariable String productId) {
        return ResponseEntity.ok(productService.getCompanyIdByProductId(productId));
    }
}