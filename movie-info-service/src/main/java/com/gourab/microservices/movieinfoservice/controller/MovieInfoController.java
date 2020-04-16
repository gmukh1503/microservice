package com.gourab.microservices.movieinfoservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gourab.microservices.movieinfoservice.model.Movie;

@RestController
public class MovieInfoController {
	
	@Value("${theMovieDB.api.key}")
	private String movieDbApiKey;
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/movie/{movieId}")
	public Movie getMovieInfo(@PathVariable String movieId) {
		Map<String,String> queryParamMap = new HashMap<String,String>();
		queryParamMap.put("api_key",movieDbApiKey);
		
		String movieDbUrl="https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+movieDbApiKey;
		System.out.println("----------api_key :"+movieDbApiKey);
		System.out.println("----------URL :"+movieDbUrl);
		Movie m = restTemplate.getForObject(movieDbUrl,Movie.class);
		//m.setMovieId(movieId);
		//m.setMovieName("Dil To Pagal Hai");
		return m;
	}
}
