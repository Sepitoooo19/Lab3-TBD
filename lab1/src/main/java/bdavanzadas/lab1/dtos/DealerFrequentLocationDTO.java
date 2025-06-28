package bdavanzadas.lab1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DealerFrequentLocationDTO {
    private Integer dealerId;
    private List<Double> location; // [longitude, latitude]
    private Long count;
}
