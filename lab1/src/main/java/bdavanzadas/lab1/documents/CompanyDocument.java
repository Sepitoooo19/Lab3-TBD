package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "companies")
public class CompanyDocument {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String rut;
    private String type;
    private int deliveries;
    private int failedDeliveries;
    private double totalSales;
    private GeoJsonPoint location;
    private List<String> paymentMethodIds; // IDs de métodos de pago
    private List<String> coverageAreaIds; // IDs de áreas de cobertura
}