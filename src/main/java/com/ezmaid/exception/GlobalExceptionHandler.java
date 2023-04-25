package com.ezmaid.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(MaidNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleMaidNotFoundException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleCustomerNotFoundException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(AdminNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleAdminNotFoundException(Exception ex, WebRequest rqst) {
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				ex.getMessage(),
				rqst.getDescription(false)); 
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
		
	}
	

}