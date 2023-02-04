package ul.info.digitalwallet.service.mapper;

import org.mapstruct.*;
import ul.info.digitalwallet.models.Currency;
import ul.info.digitalwallet.service.dto.CurrencyDTO;

/**
 * Mapper for the entity {@link Currency} and its DTO {@link CurrencyDTO}.
 */
@Mapper(componentModel = "spring")
public interface CurrencyMapper extends EntityMapper<CurrencyDTO, Currency> {}
