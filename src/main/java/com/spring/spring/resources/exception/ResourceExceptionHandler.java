
package com.spring.spring.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.spring.service.exceptions.ObjectNotFoundException;

public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityViolationException e, HttpServletRequest request){
		
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Error de Validação ", System.currentTimeMillis());
		for (FieldError x: e.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegretyViolationException(DataIntegrityViolationException ex, HttpServletRequest request){
	    ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),System.currentTimeMillis());
	    if(ex.getMessage().contains("EMAIL")){
	        error.setMsg("Erro de validação.");
	        error.addError("email","Email já existe!");
	    }
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<StandardError> httpMedia(HttpMediaTypeNotSupportedException e, HttpServletRequest request){
		
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	
	 
	
}
