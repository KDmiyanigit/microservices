package com.naruto.orderservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder myWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient webClient() {
        return myWebClientBuilder().build();
    }
}