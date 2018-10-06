package com.barath.app.service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.barath.app.configuration.RestEndpoints;
import com.barath.app.model.Customer;
import com.barath.app.util.JacksonUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final WebClient webClient;

    public CustomerConsumerService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Customer> saveCustomer(Customer customer) {

        if(logger.isInfoEnabled()) {
            logger.info(" consumer save customer with details {}", JacksonUtils.toJson(customer));
        }
         return  this.webClient.post()
                    .uri(RestEndpoints.NEW_CUSTOMER_ENDPOINT)
                    .body(BodyInserters.fromObject(customer))
                    .exchange()
                    .flatMap( response -> response.body(BodyExtractors.toMono(Customer.class)));

    }

    public Flux<Customer> saveCustomers(List<Customer> customers) {

        if(logger.isInfoEnabled()) {
            logger.info("consumer save customers with details {}", JacksonUtils.toJson(customers));
        }
        return  this.webClient.post()
                .uri(RestEndpoints.CUSTOMER_SERVICE_BASEPATH)
                .body(BodyInserters.fromObject(customers))
                .retrieve()
                .bodyToFlux(Customer.class);
    }

    public Mono<Customer> getCustomer(Long customerId) {

        if(logger.isInfoEnabled()) {
            logger.info(" consumer get  customer with customerid {}", customerId);
        }
        return this.webClient.get()
                .uri(RestEndpoints.GET_CUSTOMER_ENDPOINT, new Object[] {customerId})
                .exchange()
                .flatMap( clientResponse -> clientResponse.bodyToMono(Customer.class));
    }

    public Flux<Customer> getCustomer(String customerName) {

        if(logger.isInfoEnabled()) {
            logger.info(" consumer get  customer with customerName {}", customerName);
        }
        return this.webClient.get()
                    .uri( uriBuilder -> {
                        return uriBuilder.path(RestEndpoints.GET_CUSTOMER_BYNAME_ENDPOINT)
                                .queryParam("customerName", customerName)
                                .build();
                    })
                    .retrieve()
                    .bodyToFlux(Customer.class);

    }

    public Mono<Void> deleteCustomer(Long customerId) {

        return this.webClient
                    .delete()
                    .uri(RestEndpoints.DELETE_CUSTOMER_ENDPOINT)
                    .exchange()
                    .flatMap( clientResponse -> clientResponse.bodyToMono(Void.class));
    }

    public Flux<Customer> getCustomers() {

        if(logger.isInfoEnabled()) {
            logger.info(" get all the customers");
        }

        return  this.webClient
                    .get()
                    .uri(RestEndpoints.CUSTOMER_SERVICE_BASEPATH)
                    .retrieve()
                    .bodyToFlux(Customer.class);
    }
}
