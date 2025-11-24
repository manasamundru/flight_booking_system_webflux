package com.flightapp.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public Mono<ResponseEntity<String>> handleNotFound(ResourceNotFoundException ex){
		return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
	}
	 @ExceptionHandler(IllegalArgumentException.class)
	    public Mono<ResponseEntity<String>> handleBadRequest(IllegalArgumentException ex) {
	        return Mono.just(
	                ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                        .body(ex.getMessage())
	        );
	   }

}
