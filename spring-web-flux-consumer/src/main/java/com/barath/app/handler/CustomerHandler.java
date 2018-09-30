package com.barath.app.handler;

import com.barath.app.model.Customer;
import com.barath.app.service.CustomerConsumerService;
import com.barath.app.util.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

@Service
public class CustomerHandler {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final CustomerConsumerService customerConsumerService;

    public CustomerHandler(CustomerConsumerService customerConsumerService) {
        this.customerConsumerService = customerConsumerService;
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {


        return request.bodyToMono(Customer.class)
                    .flatMap(customerConsumerService::saveCustomer).flatMap( customer -> {
                    return ServerResponse.ok().body(BodyInserters.fromObject(customer));
                });
    }


    public Mono<ServerResponse> findCustomer(ServerRequest request) {

        Long customerId = Long.valueOf(request.pathVariable("customerId"));
        return Mono.just(customerId)
                .flatMap(aLong -> {
                    return this.customerConsumerService.getCustomer(customerId);
                }).flatMap(customer -> {
                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(customer));
                });

    }

    public Mono<ServerResponse> findCustomerByName(ServerRequest request) {

        Optional<String> customerOpt = request.queryParam("customerName");
        if(customerOpt.isPresent()) {

            return Mono.just(customerOpt.get())
                        .flatMap(customerName -> {
                          return  this.customerConsumerService.getCustomer(customerName).collectList();
            }).flatMap( customers -> {
                return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(customers));
            });

        }

        return ServerResponse.badRequest().build();

    }

    public Mono<ServerResponse> findCustomers(ServerRequest request) {

        return this.customerConsumerService.getCustomers().collectList()
                  .flatMap(customers -> {
                      return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(customers));
                  });
    }
}
