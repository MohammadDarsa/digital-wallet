package ul.info.digitalwallet.common.webclient.payload;

import lombok.Data;
import ul.info.digitalwallet.common.models.Card;

@Data
public class GetCardDetailsResponse extends BankBaseResponse {
    private CardDto response;
}
