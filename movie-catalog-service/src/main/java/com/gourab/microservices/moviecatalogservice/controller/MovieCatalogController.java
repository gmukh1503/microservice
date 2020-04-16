package com.gourab.microservices.moviecatalogservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.gourab.microservices.moviecatalogservice.model.CatalogItem;
import com.gourab.microservices.moviecatalogservice.model.Movie;
import com.gourab.microservices.moviecatalogservice.model.Rating;
import com.gourab.microservices.moviecatalogservice.model.UserRating;
import com.gourab.microservices.moviecatalogservice.service.MovieInfoService;
import com.gourab.microservices.moviecatalogservice.service.UserRatingService;

@RestController
public class MovieCatalogController {
	
	@Autowired
	private Builder webClientBuilder;
	
	@Autowired
	private UserRatingService userRatingService;
	
	@Autowired
	private MovieInfoService movieInfoService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/catalog/{userId}")
	//@HystrixCommand(fallbackMethod = "getAllMoviesFallback")
	public List<CatalogItem> getAllMovies(@PathVariable String userId){
		
		/*
		 * Step 1: Call RatingsDataService with userId, 
		 * to get the List of Ratings for a user.
		 * 
		 * Step 2:For each Rating call MovieInfoService,
		 * with movieId to get the Movie details.
		 * 
		 * Step 3:Put them all together.
		 */
		
		//Step 1
		List<CatalogItem> catalogItems = new ArrayList<> ();
		String ratingsDataServiceUrl = "http://ratings-data-service/movies/ratings/"+userId; //Using service discovery, using service name instead of ip address.
		UserRating userRating = userRatingService.getUserRatings(ratingsDataServiceUrl);
		List<Rating> ratingList = null;
		if (null!=userRating) {
			ratingList = userRating.getUserRatings();
			//Step 2 & 3
			String movieInfoServiceUrl="http://movie-info-service/movie/";  //Using service discovery, using service name instead of ip address.
			
			ratingList.forEach(rating->{
				
				//Using RestTemplate to do microservice call in synchronous way, this process is going to be depricated.
				Movie m = movieInfoService.getMovieInfo(movieInfoServiceUrl, rating);
				
				
				//Using WebClient to do microservice call in reactive way
				/*
				Movie m = webClientBuilder.build()	//returns a WebClient
								.get() 	//Signifies the GET request
								.uri(movieInfoServiceUrl+rating.getMovieId()) //Exact URI
								.retrieve() 	//retrieve the response 
								.bodyToMono(Movie.class) // when response is received, cast it to Movie type
								.block(); 	//Block until response is received
				*/
				
				
				CatalogItem ci = new CatalogItem();
				//ci.setRating(rating.getRating());
				ci.setMovieTitle(m.getTitle());
				ci.setOverview(m.getOverview());
				ci.setAdult(m.isAdult());
				ci.setImdb_id(m.getImdb_id());
				ci.setHomepage(m.getHomepage());
				ci.setBudget(m.getBudget());
				ci.setVote_average(m.getVote_average());
				catalogItems.add(ci);
			});
		}
		return catalogItems;
	}

	

	
	
	/*
	 * public List<CatalogItem> getAllMoviesFallback(@PathVariable String userId){
	 * List<CatalogItem> catalogItems = new ArrayList<> (); CatalogItem ci = new
	 * CatalogItem(); ci.setMovieTitle(""); ci.setOverview(""); ci.setAdult(false);
	 * ci.setImdb_id(""); ci.setHomepage(""); ci.setBudget("");
	 * ci.setVote_average(0.0); catalogItems.add(ci); return catalogItems; }
	 */
}
