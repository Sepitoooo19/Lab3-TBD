package bdavanzadas.lab1.mappers;

import bdavanzadas.lab1.documents.ProductDocument;

public class ProductMapper {

    public static ProductDocument fromProductEntity(bdavanzadas.lab1.entities.ProductEntity entity) {
        ProductDocument document = new ProductDocument();

        document.setProductId(entity.getId());
        document.setName(entity.getName());
        document.setStock(entity.getStock());
        document.setPrice(entity.getPrice());
        document.setCategory(entity.getCategory());
        document.setCompanyId(String.valueOf(entity.getCompanyId()));

        return document;
    }
}
