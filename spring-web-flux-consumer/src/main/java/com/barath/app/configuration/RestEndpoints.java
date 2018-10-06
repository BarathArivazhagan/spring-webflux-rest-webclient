package com.barath.app.configuration;

public class RestEndpoints {

    public static final String CUSTOMER_SERVICE_BASEPATH ="/customers";
    public static final String NEW_CUSTOMER_ENDPOINT = CUSTOMER_SERVICE_BASEPATH.concat("/new");
    public static final String GET_CUSTOMER_BYNAME_ENDPOINT = CUSTOMER_SERVICE_BASEPATH.concat("/byName");
    public static final String GET_CUSTOMER_ENDPOINT = CUSTOMER_SERVICE_BASEPATH.concat("/{customerId}");
    public static final String DELETE_CUSTOMER_ENDPOINT = GET_CUSTOMER_ENDPOINT;
}
