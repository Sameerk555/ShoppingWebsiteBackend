package com.ecom.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/* if id not found in service Controller then orElseThrow metod throw 
	 *    here i will give response*/
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public String HandlerResourceNotFoundException(ResourceNotFoundException ex) {
		
		
		return ex.getMessage();
	}
}
