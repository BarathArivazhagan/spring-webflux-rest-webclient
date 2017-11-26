package com.barath.app;

import java.net.URI;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.barath.app.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RestInvocationService implements GenericRestService {
	
	@Autowired
	private WebClient restClient;

	@Override
	public Flux<ResponseEntity<String>> makeRestCall(String path, HttpMethod httpMethod, HttpHeaders headers,
			Object body) {
		Mono<?> restResponse=restClient.method(httpMethod)
			.uri(path)
			.headers(headers)
			.body(BodyInserters.fromObject(body))
			.exchange()
			.flatMap( response -> response.body(BodyExtractors.toMono(Customer.class)));
		System.out.println("Client Response "+restResponse);
		if( restResponse !=null ){
			return Flux.just(new ResponseEntity<>(restResponse.block().toString(),HttpStatus.OK));
		}
		return Flux.just(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

}
