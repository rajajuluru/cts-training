package com.flightbookings;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flightbookings.Exceptions.UserNotFoundExceptionRaised;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = UserNotFoundExceptionRaised.class)

	public ResponseEntity<ResponseHelperClass> handleGenericNotFoundException(UserNotFoundExceptionRaised e) {

		System.out.println("in global exception");
		ResponseHelperClass error = new ResponseHelperClass();

		error.setData(e.getMessage());

		error.setStatus(false);

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
