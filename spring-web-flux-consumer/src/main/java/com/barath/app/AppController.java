package com.barath.app;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barath.app.model.Customer;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping(value="/customer")
public class AppController {
	
	
	
	@GetMapping(value="/async")
	public Mono<String> getAsyncResponse(){
		
		return Mono.just("hello");
	}
	
	
	@PostMapping(value="/save")
	public Mono<Customer> saveCustomer(@RequestBody Customer customer){
		
		if(customer !=null && log.isInfoEnabled()){
			log.info("Customer to be saved "+customer.toString());
			return Mono.just(customer);
		}
		return Mono.empty();
	}

}
