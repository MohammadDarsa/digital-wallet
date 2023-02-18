package ul.info.digitalwallet.common.service;

import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.models.Wallet;
import ul.info.digitalwallet.common.service.dto.WalletDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Wallet}.
 */
public interface WalletService {
    /**
     * Save a wallet.
     *
     * @param walletDTO the entity to save.
     */
    void save(User user);

    /**
     * Updates a wallet.
     *
     * @param walletDTO the entity to update.
     * @return the persisted entity.
     */
    WalletDTO update(WalletDTO walletDTO);

    /**
     * Partially updates a wallet.
     *
     * @param walletDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<WalletDTO> partialUpdate(WalletDTO walletDTO);

    /**
     * Get all the wallets.
     *
     * @return the list of entities.
     */
    List<WalletDTO> findAll();

    /**
     * Get the "id" wallet.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WalletDTO> findOne(Long id);
    Optional<Wallet> findOne(User user);
    /**
     * Delete the "id" wallet.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
