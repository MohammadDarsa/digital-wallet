package ul.info.digitalwallet.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ul.info.digitalwallet.common.models.Message;
import ul.info.digitalwallet.common.models.Profile;
import ul.info.digitalwallet.common.models.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByUser(User user);
}
