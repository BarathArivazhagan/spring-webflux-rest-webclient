package com.barath.app.controller;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barath.app.entity.Customer;
import com.barath.app.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private CustomerService customerService;

	public CustomerController( CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping(value="/customer", consumes= MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Mono<Customer> saveCustomer(@RequestBody Customer customer){
		return this.customerService.saveCustomer(customer);
	}

	@PostMapping(value="/customers")
	public Flux<Customer> saveCustomers(@RequestBody List<Customer> customers){
		return this.customerService.saveCustomers(customers);
	}

	@PutMapping(value="/customer",consumes= MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Mono<Customer> updateCustomer(@RequestBody Customer customer){
		return this.customerService.saveCustomer(customer);
	}

	@GetMapping(value="/customers")
	public Flux<Customer> findAllCustomers(){
		return this.customerService.getCustomers();
	}

	@GetMapping(value= "/customer/{customerId}")
	public Mono<Customer> findCustomerById(@PathVariable Long customerId){
		return this.customerService.getCustomer(customerId);
	}

	@GetMapping(value="/customer/byName")
	public Flux<Customer> saveCustomer(@RequestParam String customerName){
		return this.customerService.getCustomer(customerName);
	}

	@DeleteMapping(value= "/customer/{customerId}")
	public Mono<Void> deleteCustomer(@PathVariable Long customerId ) {
		return this.customerService.deleteCustomer(customerId);
	}

}
