package bdavanzadas.lab1.documentServices;

import bdavanzadas.lab1.documents.ProductDocument;
import bdavanzadas.lab1.documentRepositories.ProductDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductDocumentService {

    private final ProductDocumentRepository productRepository;

    @Autowired
    public ProductDocumentService(ProductDocumentRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDocument> getAll() {
        return productRepository.findAll();
    }

    public Optional<ProductDocument> getById(String id) {
        return productRepository.findById(id);
    }

    public List<ProductDocument> getByStockGreaterThan(int stock) {
        return productRepository.findByStockGreaterThan(stock);
    }

    public List<ProductDocument> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<String> getAllDistinctCategories() {
        return productRepository.findAllCategoriesOnly().stream()
                .map(ProductDocument::getCategory)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<ProductDocument> getByCompanyId(String companyId) {
        return productRepository.findByCompanyId(companyId);
    }

    public boolean existsByProductId(Integer productId) {
        return productRepository.existsByProductId(productId);
    }

    public Optional<ProductDocument> getByProductId(Integer productId) {
        return productRepository.findByProductId(productId);
    }

    public ProductDocument save(ProductDocument product) {
        return productRepository.save(product);
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
