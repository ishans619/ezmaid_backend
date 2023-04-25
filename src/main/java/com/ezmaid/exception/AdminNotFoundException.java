package com.ezmaid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AdminNotFoundException extends RuntimeException {
	
	public AdminNotFoundException(String message) {
		
		super(message);
		
	}

}
