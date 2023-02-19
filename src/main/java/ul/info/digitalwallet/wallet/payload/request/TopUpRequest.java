package ul.info.digitalwallet.wallet.payload.request;

import lombok.Data;

@Data
public class TopUpRequest {
    private String currency;
    private String cvv;
    private String pan;
    private String exp;
    private Double amount;
}
