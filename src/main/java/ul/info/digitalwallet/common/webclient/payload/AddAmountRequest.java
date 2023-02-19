package ul.info.digitalwallet.common.webclient.payload;

import lombok.Data;

@Data
public class AddAmountRequest {
    private Long id;
    private Double amount;
}
