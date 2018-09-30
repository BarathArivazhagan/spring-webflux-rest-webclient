package com.barath.app.service;

import com.barath.app.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;

@Service
public class RestInvocationService implements GenericRestService {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final WebClient restClient;

	public RestInvocationService(WebClient restClient) {
		this.restClient = restClient;
	}

	@Override
	public Flux<ResponseEntity<String>> makeRestCall(String path, HttpMethod httpMethod, HttpHeaders headers,
			Object body) {
		Mono<?> restResponse=restClient.method(httpMethod)
			.uri(path)
			.body(BodyInserters.fromObject(body))
			.exchange()
			.flatMap( response -> response.body(BodyExtractors.toMono(Customer.class)));
		logger.info("Client Response "+restResponse);
		if( restResponse !=null ){
			return Flux.just(new ResponseEntity<>(restResponse.block().toString(),HttpStatus.OK));
		}
		return Flux.just(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

}
