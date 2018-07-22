package com.thinkbit.api.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkbit.api.example.config.RestUriConfig;
import com.thinkbit.api.example.define.Direction;
import com.thinkbit.api.example.model.AccountBalance;
import com.thinkbit.api.example.model.OrderActive;
import com.thinkbit.api.example.model.OrderCancel;
import com.thinkbit.api.example.model.OrderCreate;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

;

public class ThinkBitAPIClient {
    @Autowired
    private RestUriConfig restUriConfig;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebClient webClient;

    @Value("${thinkbit.key}")
    private String apiKey;

    @Value("${thinkbit.secret}")
    private String apiSecret;

    private Optional<MultiValueMap<String, String>> signature(String key, String secret, Map<String, Object> body) {
        String json;
        try {
            json = this.objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
        HmacUtils hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, secret);
        String signature = hmac.hmacHex(json);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.put("signature", Collections.singletonList(signature));
        headers.put("api_key", Collections.singletonList(key));
        return Optional.of(headers);
    }

    public Mono<AccountBalance> accountBalance(String currency) {
        RequestBody body = new RequestBody();
        body.put("currency", currency);
        body.put("nonce", DateTime.now().getMillis());
        Mono<RequestBody> bodyMono = Mono.just(body);

        return this.webClient.post()
                .uri(this.restUriConfig.getAccountBalanceURI())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .headers(httpHeaders -> {
                    Optional<MultiValueMap<String, String>> optional = this.signature(apiKey, apiSecret, body);
                    httpHeaders.addAll(optional.orElseThrow(NullPointerException::new));
                })
                .body(bodyMono, RequestBody.class)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToMono(AccountBalance.class);
    }

    public Mono<OrderCancel> orderCancel(String orderId) {
        RequestBody body = new RequestBody();
        body.put("order_id", orderId);
        body.put("nonce", DateTime.now().getMillis());
        Mono<RequestBody> bodyMono = Mono.just(body);

        return this.webClient.post()
                .uri(this.restUriConfig.getOrderCancelURI())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .headers(httpHeaders -> {
                    Optional<MultiValueMap<String, String>> optional = this.signature(apiKey, apiSecret, body);
                    httpHeaders.addAll(optional.orElseThrow(NullPointerException::new));
                })
                .body(bodyMono, RequestBody.class)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToMono(OrderCancel.class);
    }

    public Mono<OrderCreate> orderCreate(String code, Direction direction, BigDecimal price, BigDecimal amount) {
        RequestBody body = new RequestBody();
        body.put("pair", code);
        if (price != null) {
            body.put("type", "limit");
            body.put("price", price);
        } else
            body.put("type", "market");
        body.put("side", direction.toString());
        body.put("amount", amount);
        body.put("nonce", DateTime.now().getMillis());

        Mono<RequestBody> bodyMono = Mono.just(body);
        return this.webClient.post()
                .uri(this.restUriConfig.getOrderCreateURI())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .headers(httpHeaders -> {
                    Optional<MultiValueMap<String, String>> optional = this.signature(apiKey, apiSecret, body);
                    httpHeaders.addAll(optional.orElseThrow(NullPointerException::new));
                })
                .body(bodyMono, RequestBody.class)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToMono(OrderCreate.class);
    }

    public Mono<OrderActive> orderActive(String code) {
        RequestBody body = new RequestBody();
        body.put("pair", code);
        body.put("nonce", DateTime.now().getMillis());

        Mono<RequestBody> bodyMono = Mono.just(body);
        return this.webClient.post()
                .uri(this.restUriConfig.getOrderActiveURI())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .headers(httpHeaders -> {
                    Optional<MultiValueMap<String, String>> optional = this.signature(apiKey, apiSecret, body);
                    httpHeaders.addAll(optional.orElseThrow(NullPointerException::new));
                })
                .body(bodyMono, RequestBody.class)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToMono(OrderActive.class);
    }
}

class RequestBody extends HashMap<String, Object> {

}
