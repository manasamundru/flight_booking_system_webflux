package com.flightapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.flightapp.dto.FlightSearchRequest;
import com.flightapp.dto.FlightsRequest;
import com.flightapp.entities.Flights;
import com.flightapp.services.FlightService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flight")
public class FlightController {
	private final FlightService flightservice;

	public FlightController(FlightService flightservice) {
		this.flightservice = flightservice;
	}

	@PostMapping("/airline/inventory")
	public Mono<ResponseEntity<String>> addInventory(@RequestBody @Valid FlightsRequest request) {
		Flights flight = Flights.builder().id(request.getFlightsRequestId())
				.airlineId(request.getFlightsRequestAirlineId()).fromPlace(request.getFlightsRequestFromPlace())
				.toPlace(request.getFlightsRequestToPlace()).journeyDate(request.getFlightsRequestJourneyDate())
				.departureTime(request.getFlightsRequestDepartureTime())
				.arrivalTime(request.getFlightsRequestArrivalTime())
				.roundTripAvailable(request.isFlightsRequestRoundTripAvailable())
				.priceOneWay(request.getFlightsRequestPriceOneWay())
				.priceRoundTrip(request.getFlightsRequestPriceRoundTrip())
				.totalSeats(request.getFlightsRequestTotalSeats()).mealOptions(request.getFlightsRequestMealOptions())
				.availableSeats(request.getFlightsRequestTotalSeats()).build();
		return flightservice.addInventory(flight)
				.map(id -> ResponseEntity.status(HttpStatus.CREATED).body(request.getFlightsRequestId()));
	}

	@PostMapping("/search")
	public Flux<Flights> searchFlights(@RequestBody @Valid FlightSearchRequest req) {
		return flightservice.searchFlights(req);
	}

}
