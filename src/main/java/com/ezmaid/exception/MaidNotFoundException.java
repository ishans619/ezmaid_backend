package com.ezmaid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MaidNotFoundException extends RuntimeException {
	
	public MaidNotFoundException(String message) {
		super(message);
	}
}
