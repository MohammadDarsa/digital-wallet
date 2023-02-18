package ul.info.digitalwallet.common.service.webclient.payload;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetCardDetailsRequest {
    private String pan;
    private String cvv;
    private LocalDate exp;
}
