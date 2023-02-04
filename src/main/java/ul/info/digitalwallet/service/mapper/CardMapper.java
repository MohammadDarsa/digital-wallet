package ul.info.digitalwallet.service.mapper;

import org.mapstruct.*;
import ul.info.digitalwallet.models.Card;
import ul.info.digitalwallet.models.Wallet;
import ul.info.digitalwallet.service.dto.CardDTO;
import ul.info.digitalwallet.service.dto.WalletDTO;

/**
 * Mapper for the entity {@link Card} and its DTO {@link CardDTO}.
 */
@Mapper(componentModel = "spring")
public interface CardMapper extends EntityMapper<CardDTO, Card> {
    @Mapping(target = "wallet", source = "wallet", qualifiedByName = "walletId")
    CardDTO toDto(Card s);

    @Named("walletId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    WalletDTO toDtoWalletId(Wallet wallet);
}
