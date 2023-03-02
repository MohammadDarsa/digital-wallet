package ul.info.digitalwallet.wallet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ul.info.digitalwallet.common.models.Message;
import ul.info.digitalwallet.common.service.MessageService;
import ul.info.digitalwallet.common.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/api/messages")
    public List<Message> getMessagesByUser() {
        return messageService.getMessagesByUser();
    }
}
