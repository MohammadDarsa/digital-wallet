package ul.info.digitalwallet.common.exceptions;

import lombok.NoArgsConstructor;

public class BalanceNotFoundException extends BaseException{
    public BalanceNotFoundException(String currency, String userName) {
        super("BALANCE-002", "Balance of currency " + currency + " for user "+ userName +" not found. ", "Please create a new balance.");
    }
    public BalanceNotFoundException() {
        super("BALANCE-003", "Balance not found.", "please check again.");
    }
}
