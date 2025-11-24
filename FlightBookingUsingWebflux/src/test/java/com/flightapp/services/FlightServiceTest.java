package com.flightapp.services;

import com.flightapp.dto.FlightSearchRequest;
import com.flightapp.entities.Airline;
import com.flightapp.entities.Flights;
import com.flightapp.exceptions.ResourceNotFoundException;
import com.flightapp.repositories.AirlineRepository;
import com.flightapp.repositories.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalTime;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

	@Mock
	FlightRepository flightRepository;
	@Mock
	AirlineRepository airlineRepository;
	@InjectMocks
	FlightService flightService;
	Flights flight;

	@BeforeEach
	void setup() {
		flight = Flights.builder().id("F101").airlineId("A101").fromPlace("Delhi").toPlace("Mumbai")
				.journeyDate(LocalDate.now()).departureTime(LocalTime.of(10, 0)).arrivalTime(LocalTime.of(12, 0))
				.totalSeats(100).availableSeats(50).priceOneWay(5000).build();
	}

	@Test
	void testAddInventory_success() {
		Airline a = new Airline("A101", "Indigo");
		Mockito.when(airlineRepository.findById("A101")).thenReturn(Mono.just(a));
		Mockito.when(flightRepository.save(flight)).thenReturn(Mono.just(flight));
		StepVerifier.create(flightService.addInventory(flight)).expectNext("F101").verifyComplete();
	}
	@Test
	void testAddInventory_airlineNotFound() {
		Mockito.when(airlineRepository.findById("A101")).thenReturn(Mono.empty());
		StepVerifier.create(flightService.addInventory(flight)).expectError(ResourceNotFoundException.class).verify();
	}
	@Test
	void testSearchFlights_success() {
		FlightSearchRequest req = FlightSearchRequest.builder().fromPlace("Delhi").toPlace("Mumbai")
				.journeyDate(LocalDate.now()).build();

		Mockito.when(flightRepository.findByFromPlaceAndToPlaceAndJourneyDate(req.getFromPlace(), req.getToPlace(),
				req.getJourneyDate())).thenReturn(Flux.just(flight));
		StepVerifier.create(flightService.searchFlights(req)).expectNext(flight).verifyComplete();
	}
	@Test
	void testGetFlightById_success() {
		Mockito.when(flightRepository.findById("F101")).thenReturn(Mono.just(flight));
		StepVerifier.create(flightService.getFlightById("F101")).expectNext(flight).verifyComplete();
	}
	@Test
	void testDecrementSeats_success() {
		Mockito.when(flightRepository.findById("F101")).thenReturn(Mono.just(flight));
		flight.setAvailableSeats(50);
		Mockito.when(flightRepository.save(flight)).thenReturn(Mono.just(flight));
		StepVerifier.create(flightService.decrementSeats("F101", 2)).expectNext(flight).verifyComplete();
	}

	@Test
	void testDecrementSeats_notEnough() {
		Mockito.when(flightRepository.findById("F101")).thenReturn(Mono.just(flight));
		StepVerifier.create(flightService.decrementSeats("F101", 1000)).expectError(RuntimeException.class).verify();
	}

	@Test
	void testIncrementSeats_success() {
		Mockito.when(flightRepository.findById("F101")).thenReturn(Mono.just(flight));
		flight.setAvailableSeats(50);
		Mockito.when(flightRepository.save(flight)).thenReturn(Mono.just(flight));
		StepVerifier.create(flightService.incrementSeats("F101", 10)).expectNext(flight).verifyComplete();
	}
}
