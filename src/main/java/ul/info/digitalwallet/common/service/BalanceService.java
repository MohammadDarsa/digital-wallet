package ul.info.digitalwallet.common.service;

import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.service.dto.BalanceDTO;
import ul.info.digitalwallet.common.models.Balance;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Balance}.
 */
public interface BalanceService {
    /**
     * Save a balance.
     *
     * @param currency
     * @param user
     */
    void save(String currency, User user);

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

    List<BalanceDTO> findByWalletId(Long id);
}
