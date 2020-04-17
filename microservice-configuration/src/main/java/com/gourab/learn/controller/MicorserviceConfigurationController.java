package com.gourab.learn.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gourab.learn.config.DbConfig;

@RestController
public class MicorserviceConfigurationController {
	
	@Value("${greet.message}")
	private String greetMsg;
	
	@Value("${greet.list.persons}")
	List<String> personlist;
	
	@Value("#{${greet.dbdetails}}")
	Map<String,String> dbDetailsMap;
	
	@Autowired
	private DbConfig dbConfiguration;
	
	@GetMapping("/greet")
	public String getGreetingsMsg() {
		return  greetMsg + personlist.toString(); 
	}
	
	@GetMapping("/dbdetails")
	public String getDbDetails() {
		return dbDetailsMap.toString();
	}
	
	@GetMapping("/dbconfig")
	public String getDbConfigUsingConfigurationProperties() {
		return dbConfiguration.toString();
	}
	
}
