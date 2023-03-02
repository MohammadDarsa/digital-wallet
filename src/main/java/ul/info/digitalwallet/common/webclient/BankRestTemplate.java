package ul.info.digitalwallet.common.webclient;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ul.info.digitalwallet.common.payload.response.BaseResponse;
import ul.info.digitalwallet.common.webclient.payload.*;

@Service
public class BankRestTemplate {
    private final WebClient webClient;

    public BankRestTemplate(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/api").build();
    }

    public BaseResponse<CardDto> getDetails(Long id){
        return this.webClient.get().uri("/get-card-details/{id}",id).retrieve().bodyToMono(BaseResponse.class).block();
    }
    public GetCardDetailsResponse getDetails(GetCardDetailsRequest t){
        return this.webClient.post().uri("/get-card-details")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .body(Mono.just(t), GetCardDetailsRequest.class)
                        .retrieve()
                .bodyToMono(GetCardDetailsResponse.class).block();
    }
    public AddAmountResponse addAmount(AddAmountRequest request){
        return this.webClient.post().uri("/add-amount")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(request), AddAmountRequest.class)
                .retrieve()
                .bodyToMono(AddAmountResponse.class).block();
    }
    public BankBaseResponse transfer(TransferRequest request) {
        return this.webClient.post().uri("/transfer")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(request), TransferRequest.class)
                .retrieve()
                .bodyToMono(BankBaseResponse.class).block();
    }
}

