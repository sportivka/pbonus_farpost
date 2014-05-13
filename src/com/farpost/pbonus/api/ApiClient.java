package com.farpost.pbonus.api;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.farpost.pbonus.api.Authorization;

public class ApiClient {

	public String Authorization(Authorization auth){
	String url = "https://api.p-bonus.ga/v1/{query}";
		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();
		// Add the String message converter
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		// Make the POST Request 
		String response = restTemplate.postForObject(url, auth, String.class,auth.getTypeLogin());
		return response;
	}
}
