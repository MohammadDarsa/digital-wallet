package ul.info.digitalwallet.wallet.service.observer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ul.info.digitalwallet.common.models.Message;
import ul.info.digitalwallet.common.models.User;
import ul.info.digitalwallet.common.repository.MessageRepository;
import ul.info.digitalwallet.common.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class MessageObserver implements Observer{
    private User user;
    private final MessageRepository messageRepository;
    public void setUser(User u){
        this.user = u;
    }
    public void update(String message){
        Message newMessage = new Message();
        newMessage.setSender("LitPay");
        newMessage.setMessageBody(message);
        newMessage.setUser(user);
        messageRepository.save(newMessage);
    }
}