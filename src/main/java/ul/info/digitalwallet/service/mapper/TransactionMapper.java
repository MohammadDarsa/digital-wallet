package ul.info.digitalwallet.service.mapper;

import org.mapstruct.*;
import ul.info.digitalwallet.models.Transaction;
import ul.info.digitalwallet.models.Wallet;
import ul.info.digitalwallet.service.dto.TransactionDTO;
import ul.info.digitalwallet.service.dto.WalletDTO;

/**
 * Mapper for the entity {@link Transaction} and its DTO {@link TransactionDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {
    @Mapping(target = "wallet", source = "wallet", qualifiedByName = "walletId")
    TransactionDTO toDto(Transaction s);

    @Named("walletId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    WalletDTO toDtoWalletId(Wallet wallet);
}
