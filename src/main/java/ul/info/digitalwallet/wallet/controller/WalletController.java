package ul.info.digitalwallet.wallet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ul.info.digitalwallet.common.payload.response.BaseResponse;
import ul.info.digitalwallet.common.payload.util.ResponseFactory;
import ul.info.digitalwallet.common.service.WalletService;
import ul.info.digitalwallet.wallet.payload.response.GetWalletResponse;
import ul.info.digitalwallet.wallet.service.DigitalWalletService;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final DigitalWalletService walletService;

    @GetMapping("/get-wallet-details")
    public BaseResponse<GetWalletResponse> getWalletDetails() {
        return ResponseFactory.success(walletService.getWalletDetails());
    }
}
