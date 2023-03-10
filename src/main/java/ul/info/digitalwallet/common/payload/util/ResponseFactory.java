package ul.info.digitalwallet.common.payload.util;

import org.springframework.stereotype.Component;
import ul.info.digitalwallet.common.payload.response.BaseResponse;

@Component
public class ResponseFactory {
    public static <T> BaseResponse<T> success(T response) {
        return new BaseResponse<T>().setResponse(response).setCode("0").setMessage("Success").setStatus("SUCCESS");
    }

    public static BaseResponse fail(String status, String code, String message) {
        return new BaseResponse().setStatus(status).setCode(code).setMessage(message);
    }
}
