package ul.info.digitalwallet.wallet.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.service.*;
import ul.info.digitalwallet.common.service.dto.BalanceDTO;
import ul.info.digitalwallet.common.service.dto.ProfileDTO;
import ul.info.digitalwallet.common.service.dto.TransactionDTO;
import ul.info.digitalwallet.common.service.dto.WalletDTO;
import ul.info.digitalwallet.wallet.payload.request.CreateNewBalanceRequest;
import ul.info.digitalwallet.wallet.payload.response.CreateNewBalanceResponse;
import ul.info.digitalwallet.wallet.payload.response.GetWalletResponse;
import ul.info.digitalwallet.wallet.service.DigitalWalletService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DigitalWalletServiceImpl implements DigitalWalletService {

    private final UserService userService;
    private final WalletService walletService;
    private final TransactionService transactionService;
    private final ProfileService profileService;
    private final BalanceService balanceService;

    @Override
    public GetWalletResponse getWalletDetails() {
        User user = userService.getAuthenticatedUser();
        log.info("Getting wallet details for user {}", user.getUsername());
        log.info("Getting wallet...");
        WalletDTO wallet = walletService.findOne(user);
        log.info("Getting transactions...");
        List<TransactionDTO> transactions = transactionService.findAllByWalletId(wallet.getId());
        log.info("Getting profile...");
        ProfileDTO profile = profileService.findByUser(user);
        log.info("Getting balances...");
        List<BalanceDTO> balances = balanceService.findByWalletId(wallet.getId());
        return new GetWalletResponse(wallet, balances, transactions, profile);
    }

    @Override
    public CreateNewBalanceResponse createNewBalance(CreateNewBalanceRequest request) {
        User user = userService.getAuthenticatedUser();
        log.info("Creating new balance of currency {} for user {}", request.getCurrency(), user.getUsername());
        BalanceDTO balance = balanceService.save(request.getCurrency(), user);
        return new CreateNewBalanceResponse(balance);
    }
}
