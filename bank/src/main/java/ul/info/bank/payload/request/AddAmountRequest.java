package ul.info.bank.payload.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddAmountRequest {
    private Long id;
    private Double amount;
}
