package ul.info.digitalwallet.wallet.exceptions;

import ul.info.digitalwallet.common.exceptions.BaseException;

public class AmountInsufficientException extends BaseException {
    public AmountInsufficientException(Double amount, Double currentBalance) {
        super("BALANCE-004", "Insufficient amount to transfer ("+amount.toString()+"), your current balance is " + currentBalance.toString(), "Please top up");
    }
}
