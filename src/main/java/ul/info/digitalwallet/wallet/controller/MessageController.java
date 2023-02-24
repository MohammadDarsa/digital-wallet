package ul.info.digitalwallet.wallet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ul.info.digitalwallet.common.models.Message;
import ul.info.digitalwallet.common.service.MessageService;
import ul.info.digitalwallet.common.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/api/messages")
    public List<Message> getMessagesByUser() {
        return messageService.getMessagesByUser();
    }
}
