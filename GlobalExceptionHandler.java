package com.example.studentcrudoperations.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<String> ResourceNotFoundExceptionHandler(ResourceNotFound ex) {
		String message = ex.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handlerMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> responce = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldname = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			responce.put(fieldname, message);
		});
		return new ResponseEntity<Map<String, String>>(responce, HttpStatus.BAD_REQUEST);
	}

}
