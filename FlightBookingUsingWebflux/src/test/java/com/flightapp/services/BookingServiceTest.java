package com.flightapp.services;

import com.flightapp.dto.BookingRequest;
import com.flightapp.entities.Booking;
import com.flightapp.entities.Flights;
import com.flightapp.entities.Passenger;
import com.flightapp.repositories.BookingRepository;
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
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
	@Mock
	BookingRepository bookingRepo;
	@Mock
	FlightService flightService;
	@InjectMocks
	BookingService bookingService;
	Flights flight;
	BookingRequest request;

	@BeforeEach
	void setup() {
		flight = Flights.builder().id("F101").airlineId("A101").availableSeats(50).priceOneWay(5000)
				.journeyDate(LocalDate.now()).build();

		request = BookingRequest.builder().userEmail("xyz@mail.com").journeyDate(LocalDate.now()).mealType("VEG")
				.passengers(Arrays.asList(new Passenger("A", "F", 22, "1A"), new Passenger("B", "M", 25, "1B")))
				.build();
	}

	@Test
	void bookTicket_success() {
		Mockito.when(flightService.getFlightById("F101")).thenReturn(Mono.just(flight));
		Mockito.when(bookingRepo.findByFlightId("F101")).thenReturn(Flux.empty());
		Mockito.when(flightService.decrementSeats("F101", 2)).thenReturn(Mono.just(flight));
		Booking saved = new Booking();
		saved.setPnr("P123");
		Mockito.when(bookingRepo.save(Mockito.any())).thenReturn(Mono.just(saved));
		StepVerifier.create(bookingService.bookTicket("F101", request)).expectNext("P123").verifyComplete();
	}

}
