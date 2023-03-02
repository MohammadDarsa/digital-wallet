package ul.info.digitalwallet.common.exceptions;

public class ProfileNotFoundException extends BaseException {
    public ProfileNotFoundException(String userName) {
        super("PRF-001", "Profile of user with username "+ userName+ " not found.", "Please check again.");
    }
}
