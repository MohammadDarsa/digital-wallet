package ul.info.digitalwallet.common.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ul.info.digitalwallet.common.models.Currency;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.payload.request.AddCurrencyRequest;
import ul.info.digitalwallet.common.payload.response.GetAllCurrenciesResponse;
import ul.info.digitalwallet.common.repository.CurrencyRepository;
import ul.info.digitalwallet.common.service.UserService;
import ul.info.digitalwallet.common.service.dto.CurrencyDTO;
import ul.info.digitalwallet.common.service.mapper.CurrencyMapper;
import ul.info.digitalwallet.common.service.CurrencyService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Currency}.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final Logger log = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private final CurrencyRepository currencyRepository;

    private final CurrencyMapper currencyMapper;

    private final UserService userService;


    @Override
    public CurrencyDTO save(AddCurrencyRequest request) {
        log.debug("Request to save Currency : {}", request);
        CurrencyDTO currencyDTO = currencyMapper.addCurrencyToDto(request);
        Currency currency = currencyMapper.toEntity(currencyDTO);
        currency = currencyRepository.save(currency);
        return currencyMapper.toDto(currency);
    }

    @Override
    public CurrencyDTO update(CurrencyDTO currencyDTO) {
        log.debug("Request to update Currency : {}", currencyDTO);
        Currency currency = currencyMapper.toEntity(currencyDTO);
        currency = currencyRepository.save(currency);
        return currencyMapper.toDto(currency);
    }

    @Override
    public Optional<CurrencyDTO> partialUpdate(CurrencyDTO currencyDTO) {
        log.debug("Request to partially update Currency : {}", currencyDTO);

        return currencyRepository
            .findById(currencyDTO.getId())
            .map(existingCurrency -> {
                currencyMapper.partialUpdate(existingCurrency, currencyDTO);

                return existingCurrency;
            })
            .map(currencyRepository::save)
            .map(currencyMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrencyDTO> findAll() {
        log.debug("Request to get all Currencies");
        return currencyRepository.findAll().stream().map(currencyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CurrencyDTO> findOne(Long id) {
        log.debug("Request to get Currency : {}", id);
        return currencyRepository.findById(id).map(currencyMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Currency : {}", id);
        currencyRepository.deleteById(id);
    }

    @Override
    public List<CurrencyDTO> findUserCurrencies() {
        User user = userService.getAuthenticatedUser();
        log.debug("Request to get all Currencies of user {}", user.getUsername());
        return currencyRepository.findByBalances_Wallet_User(user).stream().map(currencyMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }
}
