package ul.info.digitalwallet.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ul.info.digitalwallet.auth.payload.request.LoginRequest;
import ul.info.digitalwallet.auth.payload.request.SignupRequest;
import ul.info.digitalwallet.auth.payload.response.JwtResponse;
import ul.info.digitalwallet.auth.payload.response.MessageResponse;
import ul.info.digitalwallet.auth.service.AuthService;
import ul.info.digitalwallet.common.payload.response.BaseResponse;

import static ul.info.digitalwallet.common.payload.util.ResponseFactory.success;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/sign-in")
	public BaseResponse<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return success(authService.login(loginRequest));
	}

	@PostMapping("/sign-up")
	public BaseResponse<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		authService.signUp(signUpRequest);
		return success(new MessageResponse("User registered successfully!"));
	}
}
