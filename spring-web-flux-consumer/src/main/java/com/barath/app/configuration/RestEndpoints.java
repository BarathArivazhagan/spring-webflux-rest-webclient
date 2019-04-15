package com.barath.app.configuration;

public class RestEndpoints {


    public static final String CUSTOMER_ENDPOINT = "/customer";
    public static final String CUSTOMERS_ENDPOINT = "/customers";
    public static final String POST_CUSTOMER_ENDPOINT = CUSTOMER_ENDPOINT;
    public static final String POST_CUSTOMERS_ENDPOINT = CUSTOMERS_ENDPOINT;
    public static final String GET_CUSTOMER_BYNAME_ENDPOINT = CUSTOMER_ENDPOINT.concat("/byName");
    public static final String GET_CUSTOMER_ENDPOINT = CUSTOMER_ENDPOINT.concat("/{customerId}");
    public static final String GET_CUSTOMER_OR_DEFAULT_ENDPOINT = CUSTOMER_ENDPOINT.concat("/fallback/{customerId}");
    public static final String DELETE_CUSTOMER_ENDPOINT = CUSTOMER_ENDPOINT;
    public static final String GET_CUSTOMERS_ENDPOINT = CUSTOMERS_ENDPOINT;
    public static final String PDF_RETRIEVE_ENDPOINT = "/retrieve/pdf";
}
