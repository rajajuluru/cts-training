package com.flightbookings.controllers;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightbookings.LoginRequestHelperClass;
import com.flightbookings.ResponseHelperClass;
import com.flightbookings.Exceptions.UserNotFoundExceptionRaised;
import com.flightbookings.entities.UserCandidate;
import com.flightbookings.services.CredentialsRelatedOperations;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class RegisterController {

	@Autowired
	CredentialsRelatedOperations credentialsRelatedOperations;

	@PostMapping(value = "/register")
	public Object UserRegister(@RequestBody UserCandidate usercandidate) {
		ResponseHelperClass responseclass = new ResponseHelperClass();
		Object registerUser=null;
		try{
			 registerUser = credentialsRelatedOperations.RegisterUser(usercandidate);
				responseclass.setData(registerUser);
				responseclass.setStatus(true);
			 return responseclass;
		}
	catch(ConstraintViolationException e){
		
		responseclass.setData("Emailid already used");
		responseclass.setStatus(false);
		return responseclass;
	}
		catch(Exception e){
			
			responseclass.setData(e.getMessage());
			responseclass.setStatus(false);
			return responseclass;
		}

		
	}

	@PostMapping(value = "/login")
	public Object UserLogin(@RequestBody LoginRequestHelperClass logincredentials) throws UserNotFoundExceptionRaised {

		return credentialsRelatedOperations.UserLogin(logincredentials);

	}

}