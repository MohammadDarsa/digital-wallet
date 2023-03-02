package ul.info.digitalwallet.common.exceptions;

public class CurrencyNotFoundException extends BaseException{
    public CurrencyNotFoundException(String currency) {
        super("CURRENCY-001", "Currency of iso-code "+ currency +" not found.", "Please check available currencies.");
    }
}
