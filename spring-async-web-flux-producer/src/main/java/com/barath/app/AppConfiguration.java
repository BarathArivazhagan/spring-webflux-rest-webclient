package com.barath.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.reactive.function.client.WebClient;



@Configuration
public class AppConfiguration {
	
	@Bean
	@Description("Bean for Spring WebClient")
	public WebClient restClient(@Value("${consumer.service.url}") String serviceUrl){
		
		return WebClient.create(serviceUrl);
	}
	

}
