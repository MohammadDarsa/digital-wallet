package ul.info.digitalwallet.common.exceptions;

public class UserNotFoundException extends BaseException{
    public UserNotFoundException(String userName) {
        super("USER-001", "User with username "+ userName+ " not found.", "Please check again.");
    }
}
