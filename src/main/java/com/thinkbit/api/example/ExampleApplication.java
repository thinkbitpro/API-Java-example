package com.thinkbit.api.example;

import com.thinkbit.api.example.utils.ThinkBitAPIClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Bean
    ThinkBitAPIClient thinkBitAPIClient() {
        return new ThinkBitAPIClient();
    }

    @Bean
    WebClient webClient() {
        return WebClient.create();
    }
}
