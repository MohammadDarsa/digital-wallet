package ul.info.digitalwallet.common.service.mapper;

import org.mapstruct.*;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.models.Wallet;
import ul.info.digitalwallet.common.service.dto.UserDTO;
import ul.info.digitalwallet.common.service.dto.WalletDTO;

/**
 * Mapper for the entity {@link Wallet} and its DTO {@link WalletDTO}.
 */
@Mapper(componentModel = "spring")
public interface WalletMapper extends EntityMapper<WalletDTO, Wallet> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    WalletDTO toDto(Wallet s);

    @Named("userId")
//    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
