package com.barath.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
	
	private static final String REST_PATH="/customer/save";
	
	@Autowired
	private GenericRestService restService;
	
	@PostMapping(value="/save")
	public Mono<Customer> saveCustomer(@RequestBody Customer customer){
		
		if(customer !=null && log.isInfoEnabled()){
			log.info("Customer to be saved "+customer.toString());
			Flux<ResponseEntity<String>> response=restService.makeRestCall(REST_PATH, HttpMethod.POST, new HttpHeaders(), customer);
			System.out.println("response "+response);
			return Mono.just(customer);
		}
		return Mono.empty();
	}

}
