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

		String customerJson = customerJson();
		
		stubFor(post(urlEqualTo(RestEndpoints.NEW_CUSTOMER_ENDPOINT))
				.willReturn(aResponse()
					.withBody(customerJson)
					.withHeader("Content-Type", "application/json")));

	}

	@Test
	public void testSaveCustomer() {


		String customerJson = customerJson();
		Customer customer = (Customer)JacksonUtils.fromJson(customerJson,Customer.class);
		Mono<Customer> customerMono = this.customerConsumerService.saveCustomer(customer);

		StepVerifier.create(customerMono)
				 .expectNextCount(1)
				 .verifyComplete();

	}

	public String customerJson(){

		return  "{\n" +
				"    \"customerId\": 1,\n" +
				"    \"customerName\": \"BARATH\",\n" +
				"    \"customerGender\": \"MALE\"\n" +
				"  }";
	}



}
