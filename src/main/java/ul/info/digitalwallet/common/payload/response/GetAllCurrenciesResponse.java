package ul.info.digitalwallet.common.payload.response;

import lombok.Data;
import ul.info.digitalwallet.common.service.dto.CurrencyDTO;

import java.util.List;

@Data
public class GetAllCurrenciesResponse {
    private final List<CurrencyDTO> currencies;
}
