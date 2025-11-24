package com.flightapp.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = DummyController.class)
@Import(GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testResourceNotFoundException() {
		webTestClient.get().uri("/test/notfound").exchange().expectStatus().isNotFound().expectBody(String.class)
				.isEqualTo("Resource not found");
	}

	@Test
	void testIllegalArgumentException() {
		webTestClient.get().uri("/test/illegal").exchange().expectStatus().isBadRequest().expectBody(String.class)
				.isEqualTo("Illegal argument error");
	}

	@Test
	void testRuntimeException() {
		webTestClient.get().uri("/test/runtime").exchange().expectStatus().isBadRequest().expectBody(String.class)
				.isEqualTo("Something went wrong");
	}
}
