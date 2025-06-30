package bdavanzadas.lab1.documentControllers;

import bdavanzadas.lab1.documents.ProductDocument;
import bdavanzadas.lab1.documentServices.ProductDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controlador REST para gestionar documentos de productos.
 * Proporciona endpoints para crear, consultar y administrar productos.
 */
@RestController
@RequestMapping("/documents/product")
public class ProductDocumentController {

    /**
     * Servicio para operaciones con documentos de productos.
     */
    private final ProductDocumentService productService;

    /**
     * Constructor que inyecta el servicio de documentos de productos.
     * @param productService Servicio para gestionar documentos de productos.
     */
    @Autowired
    public ProductDocumentController(ProductDocumentService productService) {
        this.productService = productService;
    }

    /**
     * Obtiene todos los documentos de productos.
     * @return Lista de documentos de productos con código 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<ProductDocument>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    /**
     * Obtiene un documento de producto por su ID.
     * @param id ID del documento de producto.
     * @return Documento de producto encontrado o NOT FOUND si no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDocument> getById(@PathVariable String id) {
        return productService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene documentos de productos con stock positivo.
     * @return Lista de documentos de productos con stock mayor a cero.
     */
    @GetMapping("/stock-positive")
    public ResponseEntity<List<ProductDocument>> getByStockPositive() {
        return ResponseEntity.ok(productService.getByStockGreaterThan(0));
    }

    /**
     * Obtiene documentos de productos por categoría.
     * @param category Categoría de los productos.
     * @return Lista de documentos de productos en la categoría especificada.
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductDocument>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getByCategory(category));
    }


    /**
     * Obtiene todas las categorías distintas de productos.
     * @return Lista de categorías únicas de productos.
     */
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getDistinctCategories() {
        return ResponseEntity.ok(productService.getAllDistinctCategories());
    }

    /**
     * Obtiene documentos de productos asociados a una empresa específica.
     * @param companyId ID de la empresa.
     * @return Lista de documentos de productos asociados a la empresa con código 200 OK.
     */
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ProductDocument>> getByCompanyId(@PathVariable String companyId) {
        return ResponseEntity.ok(productService.getByCompanyId(companyId));
    }

    /**
     * Verifica si existe un producto con el ID especificado.
     * @param productId ID del producto a verificar.
     * @return true si existe el producto, false en caso contrario.
     */
    @GetMapping("/exists/{productId}")
    public ResponseEntity<Boolean> existsByProductId(@PathVariable Integer productId) {
        return ResponseEntity.ok(productService.existsByProductId(productId));
    }

    /**
     * Obtiene un producto por su ID numérico.
     * @param productId ID numérico del producto.
     * @return Producto con código 200 OK o NOT FOUND si no existe.
     */
    @GetMapping("/product-id/{productId}")
    public ResponseEntity<ProductDocument> getByProductId(@PathVariable Integer productId) {
        return productService.getByProductId(productId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo documento de producto.
     * @param product Documento de producto a crear.
     * @return Documento de producto creado con código 200 OK.
     */
    @PostMapping
    public ResponseEntity<ProductDocument> create(@RequestBody ProductDocument product) {
        return ResponseEntity.ok(productService.save(product));
    }

    /**
     * Elimina un producto por su ID de documento.
     * @param id ID del documento del producto a eliminar.
     * @return Respuesta vacía con código 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
