package com.barath.app.service;

import com.barath.app.configuration.RestEndpoints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;

@Service
public class PDFConsumerService {
	
	 private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	   private final WebClient webClient;

	    public PDFConsumerService(WebClient webClient) {
	        this.webClient = webClient;
	    }

	    public Mono<String> getPDF() {

	        if(logger.isInfoEnabled()) {
	            logger.info(" getting PDF ");
	        }
         	return  this.webClient.post()
	                    .uri(RestEndpoints.PDF_RETRIEVE_ENDPOINT)
	                    .exchangeToMono(response -> response.bodyToMono(String.class));

	    }

}
