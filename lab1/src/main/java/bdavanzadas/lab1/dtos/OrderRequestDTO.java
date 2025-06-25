package bdavanzadas.lab1.dtos;

import bdavanzadas.lab1.entities.OrdersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private OrdersEntity order;
    private List<Integer> productIds;
}