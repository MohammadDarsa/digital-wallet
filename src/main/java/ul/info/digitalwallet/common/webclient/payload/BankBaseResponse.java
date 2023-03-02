package ul.info.digitalwallet.common.webclient.payload;

import lombok.Data;

@Data
public class BankBaseResponse {
    private String code;
    private String status;
    private String message;
}
