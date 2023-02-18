package ul.info.digitalwallet.common.service.webclient.payload;

import lombok.Data;

@Data
public class AddAmountRequest {
    private Long id;
    private Double amount;
}
