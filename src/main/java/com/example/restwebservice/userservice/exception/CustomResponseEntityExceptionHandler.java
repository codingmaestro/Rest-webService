package com.example.restwebservice.userservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<UserException> handleUserException(Exception ex, WebRequest request) throws Exception {
		
			UserException userException = new UserException(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<UserException>(userException,HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<UserException> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		
			UserException userException = new UserException(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<UserException>(userException,HttpStatus.NOT_FOUND);
	
	}
	
   @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

	   UserException userException = new UserException(LocalDateTime.now(), ex.getFieldError().getDefaultMessage(), request.getDescription(false));
		return new ResponseEntity(userException,HttpStatus.BAD_REQUEST);

	}
   
   
}
