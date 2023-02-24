package ul.info.digitalwallet.common.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ul.info.digitalwallet.common.payload.response.BaseResponse;
import ul.info.digitalwallet.common.payload.util.ResponseFactory;
import ul.info.digitalwallet.common.service.ProfileService;
import ul.info.digitalwallet.common.service.dto.ProfileDTO;

import static ul.info.digitalwallet.common.payload.util.ResponseFactory.success;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/update-profile")
    public BaseResponse<ProfileDTO> updateProfile(@RequestBody ProfileDTO request) {
        return success(profileService.partialUpdate(request).get());
    }
}
