package com.gourab.microservices.moviecatalogservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gourab.microservices.moviecatalogservice.model.Rating;
import com.gourab.microservices.moviecatalogservice.model.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserRatingService {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getUserRatingsFallBack")
	public UserRating getUserRatings(String ratingsDataServiceUrl) {
		UserRating userRating = restTemplate.getForObject(ratingsDataServiceUrl, UserRating.class);
		return userRating;
	}
	
	public UserRating getUserRatingsFallBack(String ratingsDataServiceUrl) {
		UserRating ur = new UserRating();
		List<Rating> userRatings = new ArrayList<Rating>();
		Rating r = new Rating();
		r.setMovieId("No Movie ID");
		r.setRating(0f);
		r.setUserId("");
		userRatings.add(r);
		ur.setUserRatings(userRatings);
		return ur;
	}
}
