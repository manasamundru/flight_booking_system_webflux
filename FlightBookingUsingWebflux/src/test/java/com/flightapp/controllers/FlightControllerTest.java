package com.flightapp.controllers;

import com.flightapp.dto.FlightSearchRequest;
import com.flightapp.dto.FlightsRequest;
import com.flightapp.entities.Flights;
import com.flightapp.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;

@WebFluxTest(FlightController.class)
class FlightControllerTest {

	@MockBean
	private FlightService flightService;

	private WebTestClient webTestClient;

	@BeforeEach
	void setup() {
		webTestClient = WebTestClient.bindToController(new FlightController(flightService)).build();
	}

	@Test
	void testAddInventory_success() {
		FlightsRequest req = FlightsRequest.builder().flightsRequestId("F101").flightsRequestAirlineId("A101")
				.flightsRequestFromPlace("Delhi").flightsRequestToPlace("Mumbai").flightsRequestPriceOneWay(5000)
				.flightsRequestPriceRoundTrip(9000).flightsRequestTotalSeats(100)
				.flightsRequestMealOptions(new String[] { "VEG", "NON-VEG" }).flightsRequestJourneyDate(LocalDate.now())
				.flightsRequestDepartureTime(LocalTime.of(10, 0)).flightsRequestArrivalTime(LocalTime.of(12, 0))
				.flightsRequestRoundTripAvailable(true).build();
		Flights flight = Flights.builder().id("F101").airlineId("A101").fromPlace("Delhi").toPlace("Mumbai")
				.priceOneWay(5000).priceRoundTrip(9000).journeyDate(req.getFlightsRequestJourneyDate())
				.departureTime(req.getFlightsRequestDepartureTime()).arrivalTime(req.getFlightsRequestArrivalTime())
				.roundTripAvailable(true).totalSeats(100).availableSeats(100)
				.mealOptions(new String[] { "VEG", "NON-VEG" }).build();
		Mockito.when(flightService.addInventory(flight)).thenReturn(Mono.just("F101"));
		webTestClient.post().uri("/api/flight/airline/inventory").bodyValue(req).exchange().expectStatus().isCreated()
				.expectBody(String.class).isEqualTo("F101");

		Mockito.verify(flightService, Mockito.times(1)).addInventory(flight);
	}

	@Test
	void testSearchFlights_success() {
		FlightSearchRequest req = FlightSearchRequest.builder().fromPlace("Delhi").toPlace("Mumbai")
				.journeyDate(LocalDate.now()).build();
		Flights f = Flights.builder().id("F101").airlineId("A101").fromPlace("Delhi").toPlace("Mumbai")
				.priceOneWay(5000).build();
		Mockito.when(flightService.searchFlights(req)).thenReturn(Flux.just(f));
		webTestClient.post().uri("/api/flight/search").bodyValue(req).exchange().expectStatus().isOk()
				.expectBodyList(Flights.class).hasSize(1);
		Mockito.verify(flightService, Mockito.times(1)).searchFlights(req);
	}
}
