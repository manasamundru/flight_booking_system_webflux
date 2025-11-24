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
		Flights flight = Flights.builder().airlineId(request.getAirlineId()).fromPlace(request.getFromPlace())
				.toPlace(request.getToPlace()).journeyDate(request.getJourneyDate())
				.departureTime(request.getDepartureTime()).arrivalTime(request.getArrivalTime())
				.roundTripAvailable(request.isRoundTripAvailable()).priceOneWay(request.getPriceOneWay())
				.priceRoundTrip(request.getPriceRoundTrip()).totalSeats(request.getTotalSeats())
				.mealOptions(request.getMealOptions()).availableSeats(request.getTotalSeats()).build();
		return flightservice.addInventory(flight).map(id -> ResponseEntity.status(HttpStatus.CREATED).body(id));
	}
	@PostMapping("/search")
	public Flux<Flights> searchFlights(@RequestBody @Valid FlightSearchRequest req) {
		return flightservice.searchFlights(req);
	}

}
