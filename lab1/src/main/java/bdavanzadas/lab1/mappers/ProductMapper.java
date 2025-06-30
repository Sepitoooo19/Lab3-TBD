package bdavanzadas.lab1.mappers;

import bdavanzadas.lab1.documents.ProductDocument;



/**
 * Mapeador para convertir entidades de producto a documentos de producto.
 * Este mapeador transforma los datos de la entidad de producto
 * a un documento que puede ser almacenado en MongoDB.
 *
 * */
public class ProductMapper {

    /**
     * Convierte una entidad de producto a un documento de producto.
     *
     * @param entity Entidad de producto a convertir
     * @return Documento de producto con los datos de la entidad
     */
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
