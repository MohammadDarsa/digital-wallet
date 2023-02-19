package ul.info.digitalwallet.common.exceptions;

public class BalanceAlreadyExistsException extends BaseException{
    public BalanceAlreadyExistsException(String currency, String userName) {
        super("BALANCE-001", "Balance of currency " + currency + " for user "+ userName +" already exists. ", "Please use that balance instead.");
    }
}
