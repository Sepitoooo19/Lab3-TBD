package bdavanzadas.lab1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RapidChangeOrderDTO {

    private Integer orderId;
    private int changeCount;
    private LocalDateTime firstChange;
    private LocalDateTime lastChange;
}
