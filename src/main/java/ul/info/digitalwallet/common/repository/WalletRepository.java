package ul.info.digitalwallet.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.models.Wallet;

import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for the Wallet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);

    Optional<Wallet> findByReferenceId(UUID referenceId);

}
