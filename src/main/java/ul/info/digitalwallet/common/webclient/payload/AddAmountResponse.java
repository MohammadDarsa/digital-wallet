package ul.info.digitalwallet.common.webclient.payload;

import lombok.Data;

@Data
public class AddAmountResponse extends BankBaseResponse{
    private Double amount;
}
