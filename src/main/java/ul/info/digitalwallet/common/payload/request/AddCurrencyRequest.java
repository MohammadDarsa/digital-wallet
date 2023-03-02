package ul.info.digitalwallet.common.payload.request;

import lombok.Data;

@Data
public class AddCurrencyRequest {
    private String isoName;
    private Double exchangeValue;
    private String imagePath;
}
