package com.barath.app.configuration;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.barath.app.handler.CustomerHandler;
import com.barath.app.handler.PDFHandler;

@Configuration
public class RouterConfiguration {




    @Bean
    public RouterFunction<ServerResponse> customerRouterFunctions(final CustomerHandler customerHandler, final PDFHandler pdfHandler) {

        return RouterFunctions
                .route(GET(RestEndpoints.CUSTOMERS_ENDPOINT)
                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),customerHandler::findCustomers)
                .andRoute(GET(RestEndpoints.GET_CUSTOMER_BYNAME_ENDPOINT)
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),customerHandler::findCustomerByName)
                .andRoute(GET(RestEndpoints.GET_CUSTOMER_ENDPOINT)
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),customerHandler::findCustomer)
                .andRoute(POST(RestEndpoints.POST_CUSTOMER_ENDPOINT)
                    .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),customerHandler::saveCustomer)
                .andRoute(GET(RestEndpoints.GET_CUSTOMER_OR_DEFAULT_ENDPOINT)
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),customerHandler::findCustomerOrDefault)
                .andRoute(PUT(RestEndpoints.CUSTOMER_ENDPOINT)
                        .and(RequestPredicates.accept(MediaType.APPLICATION_PDF)),pdfHandler::getPDF);


    }

    @Bean
    public RouterFunction<ServerResponse> pdfRouterFunctions(final PDFHandler pdfHandler) {

        return RouterFunctions
                .route(POST(RestEndpoints.PDF_RETRIEVE_ENDPOINT)
                        .and(RequestPredicates.accept(MediaType.APPLICATION_PDF)),pdfHandler::getPDF);


    }
}
