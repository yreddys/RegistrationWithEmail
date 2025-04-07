package com.reg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserAlreadyRegisterHandler {

	@ExceptionHandler(UserAlreadyRegister.class)
	public ResponseEntity<String> handleUserAlreadyRegisterException(UserAlreadyRegister exception) {
	
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
	}
}
