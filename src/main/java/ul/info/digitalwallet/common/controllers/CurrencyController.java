package ul.info.digitalwallet.common.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ul.info.digitalwallet.common.payload.request.AddCurrencyRequest;
import ul.info.digitalwallet.common.payload.response.BaseResponse;
import ul.info.digitalwallet.common.payload.util.ResponseFactory;
import ul.info.digitalwallet.common.service.CurrencyService;
import ul.info.digitalwallet.common.service.dto.CurrencyDTO;

import static ul.info.digitalwallet.common.payload.util.ResponseFactory.*;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @PostMapping("/add-currency")
    public BaseResponse<CurrencyDTO> addCurrency(@RequestBody AddCurrencyRequest request) {
        return success(currencyService.save(request));
    }
}
