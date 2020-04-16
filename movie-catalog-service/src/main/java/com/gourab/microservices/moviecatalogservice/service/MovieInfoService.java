package com.gourab.microservices.moviecatalogservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gourab.microservices.moviecatalogservice.model.Movie;
import com.gourab.microservices.moviecatalogservice.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class MovieInfoService {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getMovieInfoFallBack"/*
															 * , commandProperties = {
															 * 
															 * @HystrixProperty(name =
															 * "execution.isolation.thread.timeoutInMilliseconds",value=
															 * "3000"),
															 * 
															 * @HystrixProperty(name =
															 * "circuitBreaker.requestVolumeThreshold",value="6"),
															 * 
															 * @HystrixProperty(name =
															 * "circuitBreaker.errorThresholdPercentage",value="50"),
															 * 
															 * @HystrixProperty(name =
															 * "circuitBreaker.sleepWindowsInMilliseconds",value="3000")
															 * }
															 */)
	public Movie getMovieInfo(String movieInfoServiceUrl, Rating rating) {
		Movie m = restTemplate.getForObject(movieInfoServiceUrl+rating.getMovieId(), Movie.class);
		return m;
	}
	
	public Movie getMovieInfoFallBack(String movieInfoServiceUrl, Rating rating) {
		Movie m=new Movie();
		m.setTitle("No Movie Title");
		m.setId("");
		m.setOverview("");
		m.setAdult(false);
		m.setImdb_id("");
		m.setHomepage("");
		m.setBudget("");
		m.setVote_average(0.0);
		return m;
	}
}
