package ul.info.digitalwallet.wallet.payload.request;

import lombok.Data;

@Data
public class WalletTransferRequest {
    private String referenceId;
    private String currency;
    private Double amount;
}
