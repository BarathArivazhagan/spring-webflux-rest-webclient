package com.barath.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.invoke.MethodHandles;
import java.util.List;

import com.barath.app.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.barath.app.AbstractSpringWebMVCRunner;
import com.barath.app.entity.Customer;
import com.barath.app.util.JacksonUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CustomerControllerTest extends AbstractSpringWebMVCRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static final String CUSTOMER_BASE_ENDPOINT = "/customers";
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CustomerRepository customerRepository;

	@Before
	public void setup(){
		// clear the data before each test in test db
		this.customerRepository.deleteAll();
	}


	
	
	@Test
	public void  testSaveCustomer() throws Exception {
		
		Customer customer = this.customerData().stream().findFirst().get();
		this.mockMvc
			.perform( post(CUSTOMER_BASE_ENDPOINT.concat("/new"))
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
					.content(JacksonUtils.toJson(customer)))
			.andExpect(status().isOk());
		
			
			
	}

	@Test
	public void testSaveCustomers() throws Exception {

		List<Customer> customers = this.customerData();
		this.mockMvc.perform( post(CUSTOMER_BASE_ENDPOINT)
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
							.content(JacksonUtils.toJson(customers)))
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testUpdateCustomer() throws Exception {
		Customer customer = this.customerData().stream().findFirst().get();
		this.customerRepository.save(customer);
		customer.setCustomerName("TEST");
		this.mockMvc
				.perform( put(CUSTOMER_BASE_ENDPOINT.concat("/update"))
						.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
						.content(JacksonUtils.toJson(customer)))
				.andExpect(status().isOk());

	}

	@Test
	public void testFindAllCustomers() throws Exception  {

		this.customerRepository.saveAll(this.customerData());
		this.mockMvc.perform(get(CUSTOMER_BASE_ENDPOINT))
				.andExpect(status().isOk());
	}

	@Test
	public void testFindCustomerById() throws Exception  {

		Customer customer = this.customerData().stream().findFirst().get();
		this.customerRepository.save(customer);
		Long customerId = 1L;
		this.mockMvc.perform(get(CUSTOMER_BASE_ENDPOINT.concat("/"+customerId)))
				.andExpect(status().isOk());
	}


	@Test
	public void testDeleteCustomer() throws Exception  {

		Customer customer = this.customerData().stream().findFirst().get();
		this.customerRepository.save(customer);
		Long customerId = 1L;
		this.mockMvc.perform(delete(CUSTOMER_BASE_ENDPOINT.concat("/"+customerId)))
				.andExpect(status().isOk());
	}

}
