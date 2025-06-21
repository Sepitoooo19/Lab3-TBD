package bdavanzadas.Lab3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class ProductEntity {
    private String id;
    private String name;
    private int stock;
    private double price;
    private String category;
    private String companyId;
}