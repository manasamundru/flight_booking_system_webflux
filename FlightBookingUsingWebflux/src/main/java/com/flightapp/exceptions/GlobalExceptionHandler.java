package com.flightapp.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public Mono<ResponseEntity<String>> handleNotFound(ResourceNotFoundException ex) {
		return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public Mono<ResponseEntity<String>> handleBadRequest(IllegalArgumentException ex) {
		return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
	}

//	@ExceptionHandler(RuntimeException.class)
//	public Mono<ResponseEntity<String>> handleRuntime(RuntimeException ex) {
//		return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
//	}

	@ExceptionHandler(exception = Exception.class)
	public Map<String, String> handlerException(MethodArgumentNotValidException exception) {
		Map<String, String> errorMap = new HashMap<>();
		List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
		errorList.forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errorMap.put(fieldName, message);
		});
		return errorMap;
	}
}
