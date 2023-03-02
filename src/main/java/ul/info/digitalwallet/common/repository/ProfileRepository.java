package ul.info.digitalwallet.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ul.info.digitalwallet.common.models.Profile;
import ul.info.digitalwallet.common.models.User;

import java.util.Optional;

/**
 * Spring Data JPA repository for the Profile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUser(User user);}
