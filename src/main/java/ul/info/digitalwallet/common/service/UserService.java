package ul.info.digitalwallet.common.service;

import ul.info.digitalwallet.common.models.User;

public interface UserService {
    User getAuthenticatedUser();
}
