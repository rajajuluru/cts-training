package com.flightbookings.Exceptions;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.flightbookings.ResponseHelperClass;
@ControllerAdvice
public class GlobalExceptions {
	@ExceptionHandler(value = { SQLIntegrityConstraintViolationException.class })
	public ResponseEntity<ResponseHelperClass> handleIOException() {
		ResponseHelperClass res=new ResponseHelperClass();
		res.setStatus(false);
		res.setData("dataBase insertion issue");
		return new ResponseEntity<ResponseHelperClass>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@ExceptionHandler(value = { JsonParseException.class })
	public ResponseEntity<ResponseHelperClass> handleJsonParseException(){
		ResponseHelperClass res=new ResponseHelperClass();
		res.setStatus(false);
		res.setData("Invalid Json input");
		return new ResponseEntity<ResponseHelperClass>(res, HttpStatus.BAD_REQUEST);
	}
	

}
