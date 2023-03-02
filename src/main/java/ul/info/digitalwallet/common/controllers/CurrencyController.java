package ul.info.digitalwallet.common.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;
import ul.info.digitalwallet.common.payload.request.AddCurrencyRequest;
import ul.info.digitalwallet.common.payload.response.BaseResponse;
import ul.info.digitalwallet.common.payload.response.GetAllCurrenciesResponse;
import ul.info.digitalwallet.common.payload.util.ResponseFactory;
import ul.info.digitalwallet.common.service.CurrencyService;
import ul.info.digitalwallet.common.service.dto.CurrencyDTO;

import static ul.info.digitalwallet.common.payload.util.ResponseFactory.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;

    @PostMapping("/add-currency")
    public BaseResponse<CurrencyDTO> addCurrency(@RequestBody AddCurrencyRequest request) {
        return success(currencyService.save(request));
    }

    @GetMapping("/get-all-currencies")
    public BaseResponse<GetAllCurrenciesResponse> getAllCurrencies() {
        return success(new GetAllCurrenciesResponse(currencyService.findAll()));
    }

    @GetMapping("/get-user-currencies")
    public BaseResponse<GetAllCurrenciesResponse> getUserCurrencies() {
        return success(new GetAllCurrenciesResponse(currencyService.findUserCurrencies()));
    }
}
