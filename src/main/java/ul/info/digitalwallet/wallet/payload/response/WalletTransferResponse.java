package ul.info.digitalwallet.wallet.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ul.info.digitalwallet.common.service.dto.BalanceDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransferResponse {
    private BalanceDTO balance;
}
