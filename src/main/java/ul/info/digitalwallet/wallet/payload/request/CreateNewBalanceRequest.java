package ul.info.digitalwallet.wallet.payload.request;

import lombok.Data;

@Data
public class CreateNewBalanceRequest {
    private String currency;
}
