package ul.info.digitalwallet.common.webclient.payload;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CardDto {
    private Long id;
    private String pan;
    private String cvv;
    private LocalDate exDate;
    private String currency;
    private Double amount;
}
