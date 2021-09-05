package com.flightbookings.services;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flightbookings.LoginRequestHelperClass;
import com.flightbookings.ResponseHelperClass;
import com.flightbookings.Exceptions.UserNotFoundExceptionRaised;
import com.flightbookings.entities.UserCandidate;
import com.flightbookings.repositories.UserCandidateRepository;

@Service
public class CredentialsRelatedOperations {

	@Autowired
	private UserCandidateRepository userrepository;

	@Transactional
	public Object UserLogin(LoginRequestHelperClass logincredentials) {
		System.out.println(logincredentials.toString());
		ResponseHelperClass helper = new ResponseHelperClass();
		UserCandidate frmdb = null;
		 Optional<UserCandidate> findById = userrepository.findById(logincredentials.getEmailid().trim());
		if (findById.isPresent()) {

			frmdb = findById.get();
			if (logincredentials.getEmailid().equals(frmdb.getEmailid())
					&& logincredentials.getPassword().equals(frmdb.getPassword())) {
				helper.setData("valid user");
				helper.setStatus(true);

				return helper;
			} else {

				helper.setData("Invalid Credentials");
				helper.setStatus(false);
				return helper;
			}
		} else {

			try {
				throw new UserNotFoundExceptionRaised(
						"User not found with mailId        " + logincredentials.getEmailid());
			} catch (UserNotFoundExceptionRaised e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				helper.setData("User not found with given mailId " + logincredentials.getEmailid());
				helper.setStatus(false);
				return helper;
			}

		}
	}


	public Object RegisterUser(UserCandidate usercandidate) {

		ResponseHelperClass responseclass = new ResponseHelperClass();
		Validator validator;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		Set<ConstraintViolation<UserCandidate>> validate = validator.validate(usercandidate);

		System.out.println(validate.size() + "validate.size() is");
		if (validate.size() > 0) {
			responseclass.setStatus(false);
			String message = "";
			for (ConstraintViolation<UserCandidate> s : validate) {
				message = message + s.getMessage() + "   ";
			}
			responseclass.setData(message);
			return responseclass;
		} else {
			usercandidate.setRoletype("U");
			System.out.println("inside user register api");

			System.out.println(usercandidate.toString());
			UserCandidate user = null;
			try {
			 boolean existsById = userrepository.existsById(usercandidate.getEmailid());
			 if(!existsById)
			 {
				
				user = userrepository.save(usercandidate);
			 }
			 else
			 {
					responseclass.setStatus(false);
					responseclass.setData("Users emailId "+usercandidate.getEmailid() +"   already used");
					return responseclass;
			 }

			} catch (ConstraintViolationException e) {
				// TODO: handle exception
				e.printStackTrace();
				responseclass.setStatus(false);
				responseclass.setData(e.getLocalizedMessage());
				return responseclass;

			}
			try {
				boolean status = userrepository.existsById(user.getEmailid());
				if (status) {
					responseclass.setStatus(true);
					responseclass.setData(user);
					return responseclass;
				} else {
					responseclass.setStatus(false);
					responseclass.setData("something went wrong");
					return responseclass;
				}
			} catch (Exception e) {
				// TODO: handle exception
				responseclass.setStatus(false);
				responseclass.setData("something went wrong");
				return responseclass;
			}
		}
	}

	public Optional<UserCandidate> UserDeatiilsService(String uname)
	{
		return userrepository.findById(uname);
	}
}
