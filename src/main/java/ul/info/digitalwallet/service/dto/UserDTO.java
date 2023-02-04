package ul.info.digitalwallet.service.dto;

import lombok.Data;
import ul.info.digitalwallet.models.enumeration.Role;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private Set<Role> roles;
}
