package ul.info.digitalwallet.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ul.info.digitalwallet.common.models.Balance;
import ul.info.digitalwallet.common.models.Currency;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.models.Wallet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Spring Data JPA repository for the Balance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    List<Balance> findByWallet(Wallet wallet);

    boolean existsByCurrency_IsoName(String isoName);

    boolean existsByCurrency_IsoNameAndWallet_User(String isoName, User user);

    Optional<Balance> findByCurrency_IsoNameAndWallet_User(String isoName, User user);

    Optional<Balance> findByWallet_ReferenceIdAndCurrency_IsoName(UUID referenceId, String isoName);

    List<Balance> findByWallet_Id(Long id);


}
