package com.gourab.microservices.moviecatalogservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

@Configuration
public class MovieCatalogConfig {
	
	@Bean
	@LoadBalanced
	public RestTemplate giveARestTemplate() { 
		// This is a annotated with @Bean, so it is Singleton
		return new RestTemplate();
	}
	
	@Bean
	public Builder getWebClientBuilder(){
		return WebClient.builder();
	}
}
