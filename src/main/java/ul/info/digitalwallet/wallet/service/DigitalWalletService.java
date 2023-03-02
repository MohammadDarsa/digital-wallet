package ul.info.digitalwallet.wallet.service;

import ul.info.digitalwallet.wallet.payload.request.CreateNewBalanceRequest;
import ul.info.digitalwallet.wallet.payload.request.TopUpRequest;
import ul.info.digitalwallet.wallet.payload.request.WalletTransferRequest;
import ul.info.digitalwallet.wallet.payload.response.CreateNewBalanceResponse;
import ul.info.digitalwallet.wallet.payload.response.GetWalletResponse;
import ul.info.digitalwallet.wallet.payload.response.TopUpResponse;
import ul.info.digitalwallet.wallet.payload.response.WalletTransferResponse;

import java.text.ParseException;

public interface DigitalWalletService {
    GetWalletResponse getWalletDetails();

    CreateNewBalanceResponse createNewBalance(CreateNewBalanceRequest request);

    TopUpResponse topUp(TopUpRequest request) throws ParseException;

    WalletTransferResponse transfer(WalletTransferRequest request);
}
