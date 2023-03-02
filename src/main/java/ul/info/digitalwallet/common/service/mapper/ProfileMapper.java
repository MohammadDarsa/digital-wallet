package ul.info.digitalwallet.common.service.mapper;

import org.mapstruct.*;
import ul.info.digitalwallet.common.models.Profile;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.service.dto.ProfileDTO;
import ul.info.digitalwallet.common.service.dto.UserDTO;

/**
 * Mapper for the entity {@link Profile} and its DTO {@link ProfileDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProfileMapper extends EntityMapper<ProfileDTO, Profile> {
    ProfileDTO toDto(Profile s);
}
