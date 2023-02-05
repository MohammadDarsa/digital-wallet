package ul.info.bank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private Long id;
    private String pan;
    private String cvv;
    private LocalDate exDate;
    private String currency;
    private Double amount;
}
