package ul.info.digitalwallet.service;

import ul.info.digitalwallet.models.Transaction;
import ul.info.digitalwallet.service.dto.TransactionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Transaction}.
 */
public interface TransactionService {
    /**
     * Save a transaction.
     *
     * @param transactionDTO the entity to save.
     * @return the persisted entity.
     */
    TransactionDTO save(TransactionDTO transactionDTO);

    /**
     * Updates a transaction.
     *
     * @param transactionDTO the entity to update.
     * @return the persisted entity.
     */
    TransactionDTO update(TransactionDTO transactionDTO);

    /**
     * Partially updates a transaction.
     *
     * @param transactionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TransactionDTO> partialUpdate(TransactionDTO transactionDTO);

    /**
     * Get all the transactions.
     *
     * @return the list of entities.
     */
    List<TransactionDTO> findAll();

    /**
     * Get the "id" transaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TransactionDTO> findOne(Long id);

    /**
     * Delete the "id" transaction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
