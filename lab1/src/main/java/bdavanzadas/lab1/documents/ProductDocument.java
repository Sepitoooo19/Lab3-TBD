package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Documento que representa un producto en la base de datos MongoDB.
 * Contiene información sobre el ID del producto, nombre, stock, precio,
 * categoría y el ID de la empresa a la que pertenece.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class ProductDocument {
    private String id;
    @Indexed(unique = true)
    private Integer productId;
    private String name;
    private int stock;
    private double price;
    private String category;
    private String companyId;
}