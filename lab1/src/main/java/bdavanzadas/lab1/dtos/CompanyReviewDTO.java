package bdavanzadas.lab1.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyReviewDTO {
    private Integer companyId;
    private Integer rating;
    private String comment;

}