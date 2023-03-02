package ul.info.digitalwallet.common.exceptions;

import lombok.Data;

@Data
public class BaseException extends RuntimeException{
    private String code;
    private String description;
    private String message;

    public BaseException() {
        super();
        code = "000";
        description = "An error occurred.";
        message = "Please check the logs.";
    }

    public BaseException(String code, String description, String message) {
        super(message);
        this.code = code;
        this.description = description;
        this.message = message;
    }
}
