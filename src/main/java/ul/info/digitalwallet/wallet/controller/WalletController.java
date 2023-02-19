package ul.info.digitalwallet.wallet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ul.info.digitalwallet.common.payload.response.BaseResponse;
import ul.info.digitalwallet.common.payload.util.ResponseFactory;
import ul.info.digitalwallet.common.service.WalletService;
import ul.info.digitalwallet.wallet.payload.request.CreateNewBalanceRequest;
import ul.info.digitalwallet.wallet.payload.response.CreateNewBalanceResponse;
import ul.info.digitalwallet.wallet.payload.response.GetWalletResponse;
import ul.info.digitalwallet.wallet.service.DigitalWalletService;

import static ul.info.digitalwallet.common.payload.util.ResponseFactory.*;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {
    private final DigitalWalletService walletService;

    @GetMapping("/get-wallet-details")
    public BaseResponse<GetWalletResponse> getWalletDetails() {
        return success(walletService.getWalletDetails());
    }

    @PostMapping("/create-new-balance")
    public BaseResponse<CreateNewBalanceResponse> createNewBalance(@RequestBody CreateNewBalanceRequest request) {
        return success(walletService.createNewBalance(request));
    }
}
