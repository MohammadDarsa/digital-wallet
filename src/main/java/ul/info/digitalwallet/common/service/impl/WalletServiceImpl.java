package ul.info.digitalwallet.common.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ul.info.digitalwallet.common.exceptions.WalletNotFoundException;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.models.Wallet;
import ul.info.digitalwallet.common.service.WalletService;
import ul.info.digitalwallet.common.service.dto.WalletDTO;
import ul.info.digitalwallet.common.repository.WalletRepository;
import ul.info.digitalwallet.common.service.mapper.WalletMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Wallet}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final Logger log = LoggerFactory.getLogger(WalletServiceImpl.class);

    private final WalletRepository walletRepository;

    private final WalletMapper walletMapper;


    /**
     */
    @Override
    public void save(User user) {
        log.debug("Request to create a new wallet");
        Wallet wallet = new Wallet();
        wallet.setReferenceId(UUID.randomUUID());
        wallet.setUser(user);
        walletRepository.save(wallet);
    }

    @Override
    public WalletDTO update(WalletDTO walletDTO) {
        log.debug("Request to update Wallet : {}", walletDTO);
        Wallet wallet = walletMapper.toEntity(walletDTO);
        wallet = walletRepository.save(wallet);
        return walletMapper.toDto(wallet);
    }

    @Override
    public Optional<WalletDTO> partialUpdate(WalletDTO walletDTO) {
        log.debug("Request to partially update Wallet : {}", walletDTO);

        return walletRepository
            .findById(walletDTO.getId())
            .map(existingWallet -> {
                walletMapper.partialUpdate(existingWallet, walletDTO);

                return existingWallet;
            })
            .map(walletRepository::save)
            .map(walletMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WalletDTO> findAll() {
        log.debug("Request to get all Wallets");
        return walletRepository.findAll().stream().map(walletMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WalletDTO> findOne(Long id) {
        log.debug("Request to get Wallet : {}", id);
        return walletRepository.findById(id).map(walletMapper::toDto);
    }

    @Override
    public WalletDTO findOne(User user) {
        log.debug("Get wallet by user");
        return walletRepository.findByUser(user).map(walletMapper::toDto).orElseThrow(() -> new WalletNotFoundException(user.getUsername()));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Wallet : {}", id);
        walletRepository.deleteById(id);
    }
}
