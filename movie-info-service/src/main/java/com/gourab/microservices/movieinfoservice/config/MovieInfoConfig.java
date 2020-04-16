package com.gourab.microservices.movieinfoservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MovieInfoConfig {

	@Bean
	public RestTemplate fetchRestTemplate() {
		return new RestTemplate();
	}
}
