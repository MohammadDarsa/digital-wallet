package ul.info.digitalwallet.common.webclient.payload;

import lombok.Data;

@Data
public class TransferRequest {
    private Long firstCardId;
    private String secondCardPan;
    private Double amount;
}
