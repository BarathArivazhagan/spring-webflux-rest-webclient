package com.barath.app.handler;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.barath.app.service.PDFConsumerService;

import reactor.core.publisher.Mono;

@Component
public class PDFHandler {
	

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final PDFConsumerService pdfConsumerService;
	public PDFHandler(PDFConsumerService pdfConsumerService) {
		super();
		this.pdfConsumerService = pdfConsumerService;
	}
    
	 public Mono<ServerResponse> getPDF(ServerRequest request) {


	        return ServerResponse.ok().body(BodyInserters.fromObject(this.pdfConsumerService.getPDF()));
	  }

}
