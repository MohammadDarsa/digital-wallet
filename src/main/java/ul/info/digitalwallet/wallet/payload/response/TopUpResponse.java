package ul.info.digitalwallet.wallet.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ul.info.digitalwallet.common.models.Balance;
import ul.info.digitalwallet.common.service.dto.BalanceDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopUpResponse {
    private BalanceDTO balance;
}
