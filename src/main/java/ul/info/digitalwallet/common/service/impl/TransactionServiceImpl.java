package ul.info.digitalwallet.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ul.info.digitalwallet.common.models.Transaction;
import ul.info.digitalwallet.common.repository.TransactionRepository;
import ul.info.digitalwallet.common.service.TransactionService;
import ul.info.digitalwallet.common.service.dto.TransactionDTO;
import ul.info.digitalwallet.common.service.mapper.TransactionMapper;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Transaction}.
 */
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public TransactionDTO save(TransactionDTO transactionDTO) {
        log.debug("Request to save Transaction : {}", transactionDTO);
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        transaction.setCreatedDate(Instant.now());
        transaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }

    @Override
    public TransactionDTO update(TransactionDTO transactionDTO) {
        log.debug("Request to update Transaction : {}", transactionDTO);
        Transaction transaction = transactionMapper.toEntity(transactionDTO);
        transaction = transactionRepository.save(transaction);
        return transactionMapper.toDto(transaction);
    }

    @Override
    public Optional<TransactionDTO> partialUpdate(TransactionDTO transactionDTO) {
        log.debug("Request to partially update Transaction : {}", transactionDTO);

        return transactionRepository
            .findById(transactionDTO.getId())
            .map(existingTransaction -> {
                transactionMapper.partialUpdate(existingTransaction, transactionDTO);

                return existingTransaction;
            })
            .map(transactionRepository::save)
            .map(transactionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDTO> findAll() {
        log.debug("Request to get all Transactions");
        return transactionRepository.findAll().stream().map(transactionMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionDTO> findOne(Long id) {
        log.debug("Request to get Transaction : {}", id);
        return transactionRepository.findById(id).map(transactionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Transaction : {}", id);
        transactionRepository.deleteById(id);
    }

    @Override
    public List<TransactionDTO> findAllByWalletId(Long id) {
        log.debug("Request to get all Transactions of wallet id: {}", id);
        return transactionRepository.findByWallet_Id(id).stream().map(transactionMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }
}
