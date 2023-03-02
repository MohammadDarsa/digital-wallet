package ul.info.digitalwallet.common.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ul.info.digitalwallet.common.models.Message;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.repository.MessageRepository;
import ul.info.digitalwallet.common.service.MessageService;
import ul.info.digitalwallet.common.service.UserService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final UserService userService;
    private final MessageRepository messageRepo;
    @Override
    public List<Message> getMessagesByUser() {
        return messageRepo.findByUser(userService.getAuthenticatedUser());
    }
}
