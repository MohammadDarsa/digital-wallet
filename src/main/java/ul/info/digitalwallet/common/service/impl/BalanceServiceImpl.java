package ul.info.digitalwallet.common.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ul.info.digitalwallet.common.exceptions.CurrencyNotFoundException;
import ul.info.digitalwallet.common.exceptions.WalletNotFoundException;
import ul.info.digitalwallet.common.models.Currency;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.models.Wallet;
import ul.info.digitalwallet.common.service.dto.BalanceDTO;
import ul.info.digitalwallet.common.models.Balance;
import ul.info.digitalwallet.common.repository.BalanceRepository;
import ul.info.digitalwallet.common.repository.CurrencyRepository;
import ul.info.digitalwallet.common.repository.WalletRepository;
import ul.info.digitalwallet.common.service.BalanceService;
import ul.info.digitalwallet.common.service.mapper.BalanceMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Balance}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final Logger log = LoggerFactory.getLogger(BalanceServiceImpl.class);

    private final BalanceRepository balanceRepository;

    private final BalanceMapper balanceMapper;

    private final WalletRepository walletRepository;

    private final CurrencyRepository currencyRepository;

    @Override
    public void save(String isoCode, User user) {
        log.debug("Creating a new balance with currency {} for user {}.", isoCode, user.getUsername());
        Balance balance = new Balance();
        Wallet wallet = walletRepository.findByUser(user).orElseThrow(() -> new WalletNotFoundException(user.getUsername()));
        Currency currency = currencyRepository.findByIsoName(isoCode).orElseThrow(() -> new CurrencyNotFoundException(isoCode));
        balance.setAmount(0.0);
        balance.setWallet(wallet);
        balance.setCurrency(currency);
        balanceRepository.save(balance);
    }

    @Override
    public BalanceDTO update(BalanceDTO balanceDTO) {
        log.debug("Request to update Balance : {}", balanceDTO);
        Balance balance = balanceMapper.toEntity(balanceDTO);
        balance = balanceRepository.save(balance);
        return balanceMapper.toDto(balance);
    }

    @Override
    public Optional<BalanceDTO> partialUpdate(BalanceDTO balanceDTO) {
        log.debug("Request to partially update Balance : {}", balanceDTO);

        return balanceRepository
            .findById(balanceDTO.getId())
            .map(existingBalance -> {
                balanceMapper.partialUpdate(existingBalance, balanceDTO);

                return existingBalance;
            })
            .map(balanceRepository::save)
            .map(balanceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BalanceDTO> findAll() {
        log.debug("Request to get all Balances");
        return balanceRepository.findAll().stream().map(balanceMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BalanceDTO> findOne(Long id) {
        log.debug("Request to get Balance : {}", id);
        return balanceRepository.findById(id).map(balanceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Balance : {}", id);
        balanceRepository.deleteById(id);
    }

    @Override
    public List<BalanceDTO> findByWalletId(Long id) {
        log.debug("Request to get all Balances for a wallet of id: {}", id);
        return balanceRepository.findByWallet_Id(id).stream().map(balanceMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }
}
