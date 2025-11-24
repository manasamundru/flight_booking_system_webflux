package com.flightapp.services;

import com.flightapp.entities.Airline;
import com.flightapp.repositories.AirlineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class AirlineServiceTest {

	@Mock
	private AirlineRepository airlineRepository;
	@InjectMocks
	private AirlineService airlineService;
	@Test
	void testSaveAirline_success() {
		Airline airline = Airline.builder().id("A101").name("IndiGo").build();
		Mockito.when(airlineRepository.save(airline)).thenReturn(Mono.just(airline));
		StepVerifier.create(airlineService.save(airline)).expectNext("A101").verifyComplete();
		Mockito.verify(airlineRepository, Mockito.times(1)).save(airline);
	}
	@Test
	void testFindById_success() {
		Airline airline = new Airline("A101", "IndiGo");
		Mockito.when(airlineRepository.findById("A101")).thenReturn(Mono.just(airline));
		StepVerifier.create(airlineService.findById("A101")).expectNext(airline).verifyComplete();
	}
}
