package com.barath.app;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import com.barath.app.configuration.RestEndpoints;
import com.barath.app.model.Customer;
import com.barath.app.service.CustomerConsumerService;
import com.barath.app.util.JacksonUtils;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9999)
public class CustomerConsumerServiceTest {

	@Autowired
	private WebClient webClient;

	@Autowired
	private CustomerConsumerService customerConsumerService;



	@Before
	public void setup(){

		String customerJson = customerJson("1", "BARATH", "MALE");
		
		stubFor(post(urlEqualTo(RestEndpoints.CUSTOMER_ENDPOINT))
				.willReturn(aResponse()
					.withBody(customerJson)
					.withHeader("Content-Type", "application/json")));

	}

	@Test
	public void testSaveCustomer() {
		Mono<Customer> customer1 = createCustomer("1", "BARATH", "MALE");
		Mono<Customer> customer2 = createCustomer("2", "JOHN", "MALE");
		Mono<Customer> customer3 = createCustomer("3", "JANE", "FEMALE");


	}

	private Mono<Customer> createCustomer(String id, String name, String gender) {
		Customer customer = (Customer)JacksonUtils.fromJson(customerJson(id, name, gender),Customer.class);
		Mono<Customer> customerMono = this.customerConsumerService.saveCustomer(customer);
		StepVerifier.create(customerMono)
				.expectNextCount(1)
				.verifyComplete();
		return customerMono;
	}

	public String customerJson(String id, String name, String gender){

		return  "{\n" +
				"    \"customerId\": " + id + ",\n" +
				"    \"customerName\": \"" + name + "\",\n" +
				"    \"customerGender\": \"" + gender + "\"\n" +
				"  }";
	}



}
