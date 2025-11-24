package com.flightapp.exceptions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class DummyController {

	@GetMapping("/notfound")
	public Mono<String> notFound() {
		throw new ResourceNotFoundException("Resource not found");
	}

	@GetMapping("/illegal")
	public Mono<String> illegal() {
		throw new IllegalArgumentException("Illegal argument error");
	}

	@GetMapping("/runtime")
	public Mono<String> runtime() {
		throw new RuntimeException("Something went wrong");
	}
}
