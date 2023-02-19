package ul.info.bank.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ul.info.bank.common.response.BaseResponse;
import ul.info.bank.common.util.ResponseFactory;
import ul.info.bank.model.dto.CardDto;
import ul.info.bank.model.entity.Card;
import ul.info.bank.payload.request.AddAmountRequest;
import ul.info.bank.payload.request.GetCardDetailsRequest;
import ul.info.bank.payload.request.TransferRequest;
import ul.info.bank.service.CardService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CardController {

    private final CardService cardservice;
    private final ResponseFactory rf = ResponseFactory.getInstance();

    @PostMapping("/issue-card")
    public BaseResponse<CardDto> issue(@RequestBody CardDto request) {
        return rf.success(cardservice.saveCard(request));
    }

    @GetMapping("/get-all-cards")
    public BaseResponse<List<Card>> getAll(){
        return rf.success(cardservice.getAll());
    }

    @GetMapping("/get-card-details/{id}")
    public BaseResponse<CardDto> getDetails(@PathVariable("id") Long id){
        return rf.success(cardservice.getCard(id));
    }

    @PostMapping("/get-card-details")
    public BaseResponse<CardDto> getDetails(@RequestBody GetCardDetailsRequest request){
        return rf.success(cardservice.getCard(request.getPan(),request.getCvv(),request.getExp()));
    }
    //we can use post
     @DeleteMapping("/delete-card/{id}")
     public BaseResponse<?> delete(@PathVariable("id") Long id){
        return (cardservice.deleteCard(id)) ?
                rf.success(null) :
                rf.fail("Card not found", "Delete-001", "Please try a different card id");
     }
     @PostMapping("/add-amount")
     public BaseResponse<Double> AddAmount(@RequestBody AddAmountRequest request){
         Double res = cardservice.addAmount(request.getId(), request.getAmount());
         return (res >= 0.0) ? rf.success(res) : rf.fail("error adding amount", "AddAmount-001", "try again later");
     }

     @PostMapping("/transfer")
     public BaseResponse<?> transfer(@RequestBody TransferRequest request){
         return cardservice.transfer(request.getFirstCardId(),request.getSecondCardPan(),request.getAmount()) ?
                 rf.success(null) :
                 rf.fail("Transfer failed", "Transfer-001", "Please check the values and try again.");
     }

}
