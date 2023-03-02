package ul.info.digitalwallet.auth.exceptions;

import ul.info.digitalwallet.common.exceptions.BaseException;

public class EmailAlreadyExistsException extends BaseException {
    public EmailAlreadyExistsException(String email) {
        super("AUTH-002", "User with email " + email + " already exists!", "Please choose a different email.");
    }
}
