package ul.info.digitalwallet.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ul.info.digitalwallet.common.models.Currency;
import ul.info.digitalwallet.common.models.User;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Currency entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findByIsoName(String isoName);

    List<Currency> findByBalances_Wallet_User(User user);}
