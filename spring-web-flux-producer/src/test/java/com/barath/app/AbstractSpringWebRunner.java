package com.barath.app;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.barath.app.entity.Customer;
import com.barath.app.entity.Customer.CustomerGender;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public abstract class AbstractSpringWebRunner {
	
	protected List<Customer> customerData() {
		
		Customer customer1 = new Customer(1L, "BARATH", CustomerGender.MALE);
		Customer customer2 = new Customer(2L, "BARRY", CustomerGender.MALE);
		Customer customer3 = new Customer(3L, "MALA", CustomerGender.FEMALE);
		Customer customer4 = new Customer(4L, "MALAR", CustomerGender.FEMALE);
		Customer customer5 = new Customer(5L, "VIRAT", CustomerGender.MALE);
		return Arrays.asList(customer1,customer2,customer3,customer4,customer5);
	}
	

}
