package ul.info.digitalwallet.common.exceptions;

public class WalletNotFoundException extends BaseException{
    public WalletNotFoundException(String userName) {
        super("WALLET-001", "Wallet not found for user "+ userName +". ", "Please check again.");
    }
}
