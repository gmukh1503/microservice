package com.gourab.microservices.moviecatalogservice.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String imdb_id;
	private String title;
	private boolean adult;
	private String budget;
	private double vote_average;
	private String overview;
	private String homepage;
	

}
