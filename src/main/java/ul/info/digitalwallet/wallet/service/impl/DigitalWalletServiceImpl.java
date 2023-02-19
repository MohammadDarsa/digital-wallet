package ul.info.digitalwallet.wallet.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.service.*;
import ul.info.digitalwallet.common.service.dto.BalanceDTO;
import ul.info.digitalwallet.common.service.dto.ProfileDTO;
import ul.info.digitalwallet.common.service.dto.TransactionDTO;
import ul.info.digitalwallet.common.service.dto.WalletDTO;
import ul.info.digitalwallet.wallet.payload.response.GetWalletResponse;
import ul.info.digitalwallet.wallet.service.DigitalWalletService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DigitalWalletServiceImpl implements DigitalWalletService {

    private final UserService userService;
    private final WalletService walletService;
    private final TransactionService transactionService;
    private final ProfileService profileService;
    private final BalanceService balanceService;

    @Override
    public GetWalletResponse getWalletDetails() {
        User user = userService.getAuthenticatedUser();
        WalletDTO wallet = walletService.findOne(user);
        List<TransactionDTO> transactions = transactionService.findAllByWalletId(wallet.getId());
        ProfileDTO profile = profileService.findByUser(user);
        List<BalanceDTO> balances = balanceService.findByWalletId(wallet.getId());
        return new GetWalletResponse(wallet, balances, transactions, profile);
    }
}
