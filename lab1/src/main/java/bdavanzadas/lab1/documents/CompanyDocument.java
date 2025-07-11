package bdavanzadas.lab1.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;


/**
 * Documento que representa una empresa en la base de datos MongoDB.
 * Contiene información básica de la empresa como ID, nombre, email,
 * teléfono, dirección, RUT, tipo, estadísticas de entregas y ubicación geográfica.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "companies")
public class CompanyDocument {
    private String id;

    @Indexed(unique = true)
    private Integer companyId;

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