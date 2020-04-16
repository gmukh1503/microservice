package com.gourab.microservices.ratingsdataservice.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating implements Serializable {

	private static final long serialVersionUID = 1L;
	private String movieId;
	private float rating;
	private String userId;
}
