package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.ProductDocument;
import bdavanzadas.lab1.documentServices.ProductDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents/product")
public class ProductDocumentController {

    private final ProductDocumentService productService;

    @Autowired
    public ProductDocumentController(ProductDocumentService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDocument>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDocument> getById(@PathVariable String id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stock-positive")
    public ResponseEntity<List<ProductDocument>> getByStockPositive() {
        return ResponseEntity.ok(productService.getByStockGreaterThan(0));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDocument>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getByCategory(category));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getDistinctCategories() {
        return ResponseEntity.ok(productService.getAllDistinctCategories());
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ProductDocument>> getByCompanyId(@PathVariable String companyId) {
        return ResponseEntity.ok(productService.getByCompanyId(companyId));
    }

    @GetMapping("/exists/{productId}")
    public ResponseEntity<Boolean> existsByProductId(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.existsByProductId(productId));
    }

    @GetMapping("/product-id/{productId}")
    public ResponseEntity<ProductDocument> getByProductId(@PathVariable Integer productId) {
        return productService.getByProductId(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductDocument> create(@RequestBody ProductDocument product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
