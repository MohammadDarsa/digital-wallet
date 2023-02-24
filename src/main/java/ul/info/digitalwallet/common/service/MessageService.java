package ul.info.digitalwallet.common.service;

import ul.info.digitalwallet.common.models.Message;
import ul.info.digitalwallet.common.models.User;

import java.util.List;

public interface MessageService {

    List<Message> getMessagesByUser() ;
}
