package ul.info.digitalwallet.auth.exceptions;

import ul.info.digitalwallet.common.exceptions.BaseException;

public class UserAlreadyExistsException extends BaseException {
    public UserAlreadyExistsException(String username) {
        super("AUTH-001", "User with username " + username + " already exists!", "Please choose a different username.");
    }
}
