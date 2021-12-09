package com.barath.app.service;

import com.barath.app.AbstractSpringWebRunner;
import com.barath.app.entity.Customer;
import com.barath.app.entity.Customer.CustomerGender;
import com.barath.app.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.test.StepVerifier;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomerServiceTest extends AbstractSpringWebRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
	
	Predicate<Customer> customerPredicate = cust -> "BARATH".equals(cust.getCustomerName()) && cust.getCustomerId() == 1 ;

	
	@BeforeEach
	public void setup() {
		
		List<Customer> customers = this.customerData();
		Customer cust = customers.stream().findFirst().get();
		Mockito.when(this.customerRepository.save(Mockito.any())).thenReturn(cust);		
		Mockito.when(this.customerRepository.saveAll(Mockito.any())).thenReturn(customers);
		Mockito.when(this.customerRepository.findAll()).thenReturn(customers);
		Mockito.when(this.customerRepository.findById(1L)).thenReturn(customers.stream().findFirst());
		Mockito.when(this.customerRepository.findByCustomerName("BARATH")).thenReturn(this.customerData().stream().filter( c -> "BARATH".equals(c.getCustomerName())).collect(Collectors.toList()));
	}
	
	
	@Test
	public void testSaveCustomer() {
		
		logger.info("start : test save customer");
		Customer customer = new Customer(1L, "BARATH", CustomerGender.MALE);		
		
		StepVerifier.create(this.customerService.saveCustomer(customer))
					.expectNextMatches(customerPredicate)
					.expectComplete()
					.verify();
		logger.info("end : test save customer");
	}
	
	
	@Test
	public void testSaveCustomers() {
		
		logger.info("start : test save customers");
		List<Customer> customers = this.customerData();	
		
		StepVerifier.create(this.customerService.saveCustomers(customers))
					.expectNextCount(5)
					.verifyComplete();
		logger.info("end : test save customers");
		
	}
	
	
	@Test
	public void testGetCustomers() {
		
		logger.info("start : test get customers");
		StepVerifier.create(this.customerService.getCustomers())
					.expectNextCount(5)
					.verifyComplete();
		logger.info("end : test get customers");
		
	}
	
	@Test
	public void testGetCustomerByCustomerId() {
		
		Long customerId = 1L;
		logger.info("start : test get customer by customerId {}", customerId);
		StepVerifier.create(this.customerService.getCustomer(customerId))
					.expectNextMatches(customerPredicate)
					.verifyComplete();
		logger.info("end :  test get customer by customerId {}", customerId);
		
	}
	
	@Test
	public void testGetCustomerByCustomerName() {
		
		String customerName = "BARATH";
		logger.info("start : test get customer by customerName {}", customerName);
		StepVerifier.create(this.customerService.getCustomer(customerName))
					.expectNextMatches(customerPredicate)					
					.verifyComplete();
		logger.info("end : test get customer by customerName {}", customerName);
		
	}
	

	

}
