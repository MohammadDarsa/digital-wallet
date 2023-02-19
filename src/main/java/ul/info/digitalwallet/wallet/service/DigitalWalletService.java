package ul.info.digitalwallet.wallet.service;

import ul.info.digitalwallet.wallet.payload.request.CreateNewBalanceRequest;
import ul.info.digitalwallet.wallet.payload.response.CreateNewBalanceResponse;
import ul.info.digitalwallet.wallet.payload.response.GetWalletResponse;

public interface DigitalWalletService {
    GetWalletResponse getWalletDetails();

    CreateNewBalanceResponse createNewBalance(CreateNewBalanceRequest request);
}
