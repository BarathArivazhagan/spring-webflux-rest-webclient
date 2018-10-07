package com.barath.app.service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.barath.app.entity.Customer;
import com.barath.app.repository.CustomerRepository;
import com.barath.app.util.JacksonUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> saveCustomer(Customer customer) {

        if(logger.isInfoEnabled()) {
            logger.info(" save customer with details {}", JacksonUtils.toJson(customer));
        }
        return Mono.just(this.customerRepository.save(customer));
    }

    public Flux<Customer> saveCustomers(List<Customer> customers) {

        if(logger.isInfoEnabled()) {
            logger.info(" save customers with details {}", JacksonUtils.toJson(customers));
        }
        return Flux.fromIterable(this.customerRepository.saveAll(customers));
    }

    public Mono<Customer> getCustomer(Long customerId) {

        if(logger.isInfoEnabled()) {
            logger.info(" get  customer with customerid {}", customerId);
        }
        Optional<Customer> customerOptional =this.customerRepository.findById(customerId);
        return  customerOptional.isPresent() ? Mono.just(customerOptional.get()) : Mono.empty();
    }

    public Flux<Customer> getCustomer(String customerName) {

        if(logger.isInfoEnabled()) {
            logger.info(" get  customer with customerName {}", customerName);
        }
       return Flux.fromIterable(this.customerRepository.findByCustomerName(customerName));

    }

    public Mono<Void> deleteCustomer(Long customerId) {
        this.customerRepository.deleteById(customerId);
        return Mono.empty();
    }

    public Flux<Customer> getCustomers() {

        if(logger.isInfoEnabled()) {
            logger.info(" get all the customers");
        }

        return  Flux.fromIterable(this.customerRepository.findAll());
    }
}
