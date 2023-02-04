package ul.info.digitalwallet.service.mapper;

import org.mapstruct.*;
import ul.info.digitalwallet.models.Balance;
import ul.info.digitalwallet.models.Currency;
import ul.info.digitalwallet.models.Wallet;
import ul.info.digitalwallet.service.dto.BalanceDTO;
import ul.info.digitalwallet.service.dto.CurrencyDTO;
import ul.info.digitalwallet.service.dto.WalletDTO;

/**
 * Mapper for the entity {@link Balance} and its DTO {@link BalanceDTO}.
 */
@Mapper(componentModel = "spring")
public interface BalanceMapper extends EntityMapper<BalanceDTO, Balance> {
    @Mapping(target = "currency", source = "currency", qualifiedByName = "currencyId")
    @Mapping(target = "wallet", source = "wallet", qualifiedByName = "walletId")
    BalanceDTO toDto(Balance s);

    @Named("currencyId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CurrencyDTO toDtoCurrencyId(Currency currency);

    @Named("walletId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    WalletDTO toDtoWalletId(Wallet wallet);
}
