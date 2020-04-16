package com.gourab.microservices.ratingsdataservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gourab.microservices.ratingsdataservice.model.Rating;
import com.gourab.microservices.ratingsdataservice.model.UserRating;

@RestController
public class RatingDataController {

	@RequestMapping("/rating/{movieId}")
	public Rating getMovieRating(@PathVariable String movieId) {
		Rating ratingObj = new Rating();
		ratingObj.setMovieId(movieId);
		ratingObj.setRating(9.5f);
		return ratingObj;
	}
	
	@RequestMapping("/movies/ratings/{userId}")
	public UserRating getMoviesRatedByUser(@PathVariable String userId) {
		UserRating userRatings = new UserRating ();
		List<Rating> ratingList = new ArrayList<Rating> ();
		
		Rating ratingObj1 = new Rating();
		ratingObj1.setUserId(userId);
		ratingObj1.setMovieId("100");
		//ratingObj1.setRating(9.0f);
		
		Rating ratingObj2 = new Rating();
		ratingObj2.setUserId(userId);
		ratingObj2.setMovieId("101");
		//ratingObj2.setRating(9.5f);
		
		Rating ratingObj3 = new Rating();
		ratingObj3.setUserId(userId);
		ratingObj3.setMovieId("550");
		
		ratingList.add(ratingObj1);
		ratingList.add(ratingObj2);
		ratingList.add(ratingObj3);
		
		userRatings.setUserRatings(ratingList);
		return userRatings;
		
	}
}
