package com.barath.app;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import reactor.core.publisher.Flux;

public interface GenericRestService {
	
	
	Flux<ResponseEntity<String>> makeRestCall(String path,HttpMethod httpMethod,HttpHeaders headers,Object body);

}
