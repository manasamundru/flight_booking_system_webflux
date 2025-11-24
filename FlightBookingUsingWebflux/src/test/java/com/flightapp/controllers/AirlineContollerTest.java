package com.flightapp.controllers;

import com.flightapp.dto.AirlineRequest;
import com.flightapp.entities.Airline;
import com.flightapp.services.AirlineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(AirlineController.class)
class AirlineControllerTest {
	@MockBean
	private AirlineService airlineService;
	private WebTestClient webTestClient;

	@BeforeEach
	void setup() {
		webTestClient = WebTestClient.bindToController(new AirlineController(airlineService)).build();
	}

	@Test
	void testAddAirline_success() {
		AirlineRequest req = AirlineRequest.builder().airlineRequestId("A101").airlineRequestName("IndiGo").build();
		Airline airline = Airline.builder().id("A101").name("IndiGo").build();
		Mockito.when(airlineService.save(airline)).thenReturn(Mono.just("A101"));
		webTestClient.post().uri("/api/airline").bodyValue(req).exchange().expectStatus().isCreated()
				.expectBody(String.class).isEqualTo("A101");
		Mockito.verify(airlineService, Mockito.times(1)).save(airline);
	}
}
