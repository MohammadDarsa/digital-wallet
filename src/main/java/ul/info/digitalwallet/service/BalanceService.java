package ul.info.digitalwallet.service;

import ul.info.digitalwallet.models.Balance;
import ul.info.digitalwallet.service.dto.BalanceDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Balance}.
 */
public interface BalanceService {
    /**
     * Save a balance.
     *
     * @param balanceDTO the entity to save.
     * @return the persisted entity.
     */
    BalanceDTO save(BalanceDTO balanceDTO);

    /**
     * Updates a balance.
     *
     * @param balanceDTO the entity to update.
     * @return the persisted entity.
     */
    BalanceDTO update(BalanceDTO balanceDTO);

    /**
     * Partially updates a balance.
     *
     * @param balanceDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BalanceDTO> partialUpdate(BalanceDTO balanceDTO);

    /**
     * Get all the balances.
     *
     * @return the list of entities.
     */
    List<BalanceDTO> findAll();

    /**
     * Get the "id" balance.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BalanceDTO> findOne(Long id);

    /**
     * Delete the "id" balance.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
