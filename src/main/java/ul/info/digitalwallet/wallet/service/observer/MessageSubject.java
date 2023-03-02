package ul.info.digitalwallet.wallet.service.observer;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
public class MessageSubject{
    private final MessageObserver observer;
    public void notify(String message){
        observer.update(message);
    }

}