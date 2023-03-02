package ul.info.bank.payload.request;

import lombok.Data;

@Data
public class TransferRequest {
    private Long firstCardId;
    private String secondCardPan;
    private Double amount;
}
