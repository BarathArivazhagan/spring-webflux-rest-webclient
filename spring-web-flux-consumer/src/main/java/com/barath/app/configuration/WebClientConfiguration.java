package com.barath.app.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    @Description("customer web client")
    public WebClient restClient(@Value("${customer.service.url}") String serviceUrl){

        return WebClient.create(serviceUrl);
    }

}
