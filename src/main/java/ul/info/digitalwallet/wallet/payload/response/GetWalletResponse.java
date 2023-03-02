package ul.info.digitalwallet.wallet.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ul.info.digitalwallet.common.service.dto.BalanceDTO;
import ul.info.digitalwallet.common.service.dto.ProfileDTO;
import ul.info.digitalwallet.common.service.dto.TransactionDTO;
import ul.info.digitalwallet.common.service.dto.WalletDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetWalletResponse {
    private WalletDTO wallet;
    private List<BalanceDTO> balances;
    private List<TransactionDTO> transactions;
    private ProfileDTO profile;
}
