package ul.info.digitalwallet.wallet.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ul.info.digitalwallet.common.exceptions.BaseException;
import ul.info.digitalwallet.common.models.Balance;
import ul.info.digitalwallet.common.models.Message;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.models.enumeration.TransactionType;
import ul.info.digitalwallet.common.payload.response.BaseResponse;
import ul.info.digitalwallet.common.repository.MessageRepository;
import ul.info.digitalwallet.common.service.*;
import ul.info.digitalwallet.common.service.dto.BalanceDTO;
import ul.info.digitalwallet.common.service.dto.ProfileDTO;
import ul.info.digitalwallet.common.service.dto.TransactionDTO;
import ul.info.digitalwallet.common.service.dto.WalletDTO;
import ul.info.digitalwallet.common.webclient.BankRestTemplate;
import ul.info.digitalwallet.common.webclient.payload.*;
import ul.info.digitalwallet.wallet.config.WalletConstants;
import ul.info.digitalwallet.wallet.exceptions.AmountInsufficientException;
import ul.info.digitalwallet.wallet.payload.request.CreateNewBalanceRequest;
import ul.info.digitalwallet.wallet.payload.request.TopUpRequest;
import ul.info.digitalwallet.wallet.payload.request.WalletTransferRequest;
import ul.info.digitalwallet.wallet.payload.response.CreateNewBalanceResponse;
import ul.info.digitalwallet.wallet.payload.response.GetWalletResponse;
import ul.info.digitalwallet.wallet.payload.response.TopUpResponse;
import ul.info.digitalwallet.wallet.payload.response.WalletTransferResponse;
import ul.info.digitalwallet.wallet.service.DigitalWalletService;
import ul.info.digitalwallet.wallet.service.observer.MessageSubject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DigitalWalletServiceImpl implements DigitalWalletService {

    private final UserService userService;
    private final WalletService walletService;
    private final TransactionService transactionService;
    private final ProfileService profileService;
    private final BalanceService balanceService;
    private final BankRestTemplate restTemplate;
    private final WalletConstants walletConstants;

    private final MessageSubject messageSubject;

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

    @Override
    public TopUpResponse topUp(TopUpRequest request) throws ParseException {
        User user = userService.getAuthenticatedUser();
        log.info("top up for user {}", user.getUsername());
        Balance balance = balanceService.findBalanceByCurrencyAndUser(request.getCurrency(), user);
        GetCardDetailsRequest getCardDetailsRequest = new GetCardDetailsRequest();
        getCardDetailsRequest.setCvv(request.getCvv());
        getCardDetailsRequest.setExp(LocalDate.parse(request.getExp() + "/01", DateTimeFormatter.ofPattern("yy/MM/dd")));
        getCardDetailsRequest.setPan(request.getPan());
        GetCardDetailsResponse getCardDetailsResponse = restTemplate.getDetails(getCardDetailsRequest);
        if(!getCardDetailsResponse.getCode().equals("0")) throw new BaseException(getCardDetailsResponse.getCode(),
                getCardDetailsResponse.getStatus(), getCardDetailsResponse.getMessage());
        Long id = getCardDetailsResponse.getResponse().getId();

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setAmount(request.getAmount());
        transferRequest.setFirstCardId(id);
        transferRequest.setSecondCardPan(getWalletPan(request.getCurrency()));

        BankBaseResponse transferResponse = restTemplate.transfer(transferRequest);
        if(!transferResponse.getCode().equals("0")) throw new BaseException(transferResponse.getCode(),
                transferResponse.getStatus(), transferResponse.getMessage());

        BalanceDTO balanceDTO = new BalanceDTO();
        balanceDTO.setId(balance.getId());
        balanceDTO.setAmount(balance.getAmount() + request.getAmount());

        TransactionDTO transaction = new TransactionDTO();
        transaction.setAmount(request.getAmount());
        transaction.setType(TransactionType.TOPUP);
        transaction.setDescription("Top up from card with pan " + request.getPan() + " of currency " + request.getCurrency() + ".");
        transaction.setReferenceId(UUID.randomUUID().toString());
        WalletDTO wallet = new WalletDTO();
        wallet.setId(balance.getWallet().getId());
        transaction.setWallet(wallet);
        transactionService.save(transaction);
        return new TopUpResponse(balanceService.partialUpdate(balanceDTO));
    }

    @Override
    public WalletTransferResponse transfer(WalletTransferRequest request) {
        User user = userService.getAuthenticatedUser();

        log.info("transfer from user {}, to wallet {}", user.getUsername(), request.getReferenceId());
        Balance balanceTo = balanceService.findBalanceByCurrencyAndReferenceId(request.getCurrency(), request.getReferenceId());
        Balance balanceFrom = balanceService.findBalanceByCurrencyAndUser(request.getCurrency(), user);

        Double amountFrom = balanceFrom.getAmount();
        Double amountTo = balanceTo.getAmount();
        if(amountFrom - request.getAmount() < 0 || request.getAmount() < 0) throw new AmountInsufficientException(request.getAmount(), amountFrom);


        BalanceDTO balanceDTOFrom = new BalanceDTO();
        balanceDTOFrom.setAmount(amountFrom - request.getAmount());
        Double newAmount = balanceDTOFrom.getAmount();
        messageSubject.getObserver().setUser(user);
        if(balanceFrom.getCurrency().getIsoName().equals("USD") && newAmount<=500){
            messageSubject.notify("Warning! You have "+newAmount+" $ only left in your balance");
        }else if(balanceFrom.getCurrency().getIsoName().equals("LBP") && newAmount<=3000000){
            messageSubject.notify("Warning! You have "+newAmount+" L.L only left in your balance");
        }
        balanceDTOFrom.setId(balanceFrom.getId());

        BalanceDTO balanceDTOTo = new BalanceDTO();
        balanceDTOTo.setAmount(amountTo + request.getAmount());
        balanceDTOTo.setId(balanceTo.getId());

        BalanceDTO balanceDTOOut = balanceService.partialUpdate(balanceDTOFrom);
        BalanceDTO balanceDTOIn = balanceService.partialUpdate(balanceDTOTo);

        TransactionDTO transactionIn = new TransactionDTO();
        transactionIn.setAmount(request.getAmount());
        transactionIn.setType(TransactionType.TRANSFER_IN);
        transactionIn.setDescription("Transfer in of currency " + request.getCurrency() + " from wallet "+ balanceFrom.getWallet().getReferenceId() +".");
        transactionIn.setReferenceId(UUID.randomUUID().toString());
        WalletDTO walletIn = new WalletDTO();
        walletIn.setId(balanceTo.getWallet().getId());
        transactionIn.setWallet(walletIn);
        transactionService.save(transactionIn);

        TransactionDTO transactionOut = new TransactionDTO();
        transactionOut.setAmount(request.getAmount());
        transactionOut.setType(TransactionType.TRANSFER_OUT);
        transactionOut.setDescription("Transfer out of currency " + request.getCurrency() + " to wallet "+ balanceTo.getWallet().getReferenceId() +".");
        transactionOut.setReferenceId(UUID.randomUUID().toString());
        WalletDTO walletOut = new WalletDTO();
        walletOut.setId(balanceFrom.getWallet().getId());
        transactionOut.setWallet(walletOut);
        transactionService.save(transactionOut);

        return new WalletTransferResponse(balanceDTOOut);
    }

    private String getWalletPan(String currency) {
        return switch (currency) {
            case "LBP" -> walletConstants.getBankPanLbp();
            default -> walletConstants.getBankPanUsd();
        };
    }


}
