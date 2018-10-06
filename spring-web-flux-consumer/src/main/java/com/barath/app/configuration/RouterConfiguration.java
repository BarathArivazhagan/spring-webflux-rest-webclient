package com.barath.app.configuration;

import com.barath.app.handler.CustomerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
@Configuration
public class RouterConfiguration {




    @Bean
    public RouterFunction<ServerResponse> routerFunctions(final CustomerHandler customerHandler) {

        return RouterFunctions
                .route(GET(RestEndpoints.CUSTOMER_SERVICE_BASEPATH)
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),customerHandler::findCustomers)
                .andRoute(GET(RestEndpoints.GET_CUSTOMER_BYNAME_ENDPOINT)
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),customerHandler::findCustomerByName)
                .andRoute(GET(RestEndpoints.GET_CUSTOMER_ENDPOINT)
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),customerHandler::findCustomer)
                .andRoute(POST(RestEndpoints.CUSTOMER_SERVICE_BASEPATH)
                    .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),customerHandler::saveCustomer);


    }
}
