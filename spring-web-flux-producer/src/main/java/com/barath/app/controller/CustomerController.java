package com.barath.app.controller;

import com.barath.app.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.barath.app.entity.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping(value="/customers")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	private CustomerService customerService;

	public CustomerController( CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping(value="/new")
	public Mono<Customer> saveCustomer(@RequestBody Customer customer){
		return this.customerService.saveCustomer(customer);
	}

	@PostMapping
	public Flux<Customer> saveCustomers(@RequestBody List<Customer> customers){
		return this.customerService.saveCustomers(customers);
	}

	@PutMapping(value="/update")
	public Mono<Customer> updateCustomer(@RequestBody Customer customer){
		return this.customerService.saveCustomer(customer);
	}

	@GetMapping
	public Flux<Customer> findAllCustomers(){
		return this.customerService.getCustomers();
	}

	@GetMapping(value= "/{customerId}")
	public Mono<Customer> findCustomerById(@PathVariable Long customerId){
		return this.customerService.getCustomer(customerId);
	}

	@GetMapping(value="/byName")
	public Flux<Customer> saveCustomer(@RequestParam String customerName){
		return this.customerService.getCustomer(customerName);
	}

	@DeleteMapping(value= "/{customerId}")
	public Mono<Void> deleteCustomer(@PathVariable Long customerId ) {
		return this.customerService.deleteCustomer(customerId);
	}

}
