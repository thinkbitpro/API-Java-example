package com.thinkbit.api.example.config;

import com.thinkbit.api.example.define.Period;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Component
@ConfigurationProperties("thinkbit.rest")
public class RestUriConfig {

    public static final String ACCOUNT_BALANCE = "account_balance";
    public static final String ORDER_CREATE = "order-create";
    public static final String ORDER_CANCEL = "order-cancel";
    public static final String ORDER_ACTIVE = "order-active";
    public static final String MARKET_TICKER = "market-ticker";
    public static final String MARKET_HISTORY = "market-history";

    private String server;
    private Map<String, String> endpoint;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Map<String, String> getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Map<String, String> endpoint) {
        this.endpoint = endpoint;
    }

    public URI getAccountBalanceURI() {
        return UriComponentsBuilder.fromUriString(this.server + this.endpoint.get(ACCOUNT_BALANCE)).build().encode().toUri();
    }

    public URI getOrderCreateURI() {
        return UriComponentsBuilder.fromUriString(this.server + this.endpoint.get(ORDER_CREATE)).build().encode().toUri();
    }

    public URI getOrderCancelURI() {
        return UriComponentsBuilder.fromUriString(this.server + this.endpoint.get(ORDER_CANCEL)).build().encode().toUri();
    }

    public URI getOrderActiveURI() {
        return UriComponentsBuilder.fromUriString(this.server + this.endpoint.get(ORDER_ACTIVE)).build().encode().toUri();
    }

    public URI getMarketTickerURI() {
        return UriComponentsBuilder.fromUriString(this.server + this.endpoint.get(MARKET_TICKER)).build().encode().toUri();
    }

    public URI getMarketHistoryURI(Period period) {
        return UriComponentsBuilder.fromUriString(this.server + this.endpoint.get(MARKET_HISTORY)).buildAndExpand(period.toString()).encode().toUri();
    }
}
