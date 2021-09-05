package com.flightbookings.controllers;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.flightbookings.entities.UserCandidate;
import com.flightbookings.services.CredentialsRelatedOperations;

@RestController
public class HelperController implements Serializable{
	@Autowired
	private CredentialsRelatedOperations credentialsRelatedOperations;

	@GetMapping("getUserDetails/{uname}")
	public UserCandidate getUserDetails(@PathVariable(name = "uname") String uname) {

		System.out.println(" String uname" + uname);
		Optional<UserCandidate> userDeatiilsService = credentialsRelatedOperations.UserDeatiilsService(uname);

		System.out.println(userDeatiilsService.get().toString());
		return userDeatiilsService.get();
	}
}
