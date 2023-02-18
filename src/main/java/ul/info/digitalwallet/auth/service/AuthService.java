package ul.info.digitalwallet.auth.service;

import ul.info.digitalwallet.auth.payload.request.LoginRequest;
import ul.info.digitalwallet.auth.payload.request.SignupRequest;
import ul.info.digitalwallet.auth.payload.response.JwtResponse;

public interface AuthService {
    void signUp(SignupRequest request);
    JwtResponse login(LoginRequest request);
}
