package ul.info.digitalwallet.common.service.mapper;

import org.mapstruct.*;
import ul.info.digitalwallet.common.models.Currency;
import ul.info.digitalwallet.common.payload.request.AddCurrencyRequest;
import ul.info.digitalwallet.common.service.dto.CurrencyDTO;

/**
 * Mapper for the entity {@link Currency} and its DTO {@link CurrencyDTO}.
 */
@Mapper(componentModel = "spring")
public interface CurrencyMapper extends EntityMapper<CurrencyDTO, Currency> {
    CurrencyDTO addCurrencyToDto(AddCurrencyRequest request);
}
