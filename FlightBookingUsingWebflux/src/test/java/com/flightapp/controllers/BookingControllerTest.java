package com.flightapp.controllers;

import com.flightapp.dto.BookingRequest;
import com.flightapp.entities.Booking;
import com.flightapp.entities.Passenger;
import com.flightapp.services.BookingService;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Arrays;

@WebFluxTest(BookingController.class)
class BookingControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private BookingService bookingService;

	@Test
	void bookTicket_success() {

		BookingRequest req = BookingRequest.builder().userEmail("test@mail.com").journeyDate(LocalDate.now())
				.mealType("VEG")
				.passengers(Arrays.asList(new Passenger("A", "F", 22, "1A"), new Passenger("B", "M", 25, "1B")))
				.build();
		Mockito.when(bookingService.bookTicket(Mockito.eq("F101"), Mockito.any())).thenReturn(Mono.just("PNR123"));
		webTestClient.post().uri("/api/flight/booking/F101").contentType(MediaType.APPLICATION_JSON).bodyValue(req)
				.exchange().expectStatus().isCreated().expectBody(String.class).isEqualTo("PNR123");
	}

	@Test
	void getBooking_success() {

		Booking booking = new Booking();
		booking.setPnr("PNR123");
		Mockito.when(bookingService.getByPnr("PNR123")).thenReturn(Mono.just(booking));
		webTestClient.get().uri("/api/flight/ticket/PNR123").exchange().expectStatus().isOk().expectBody()
				.jsonPath("$.pnr").isEqualTo("PNR123");
	}

	@Test
	void getBooking_notFound() {
		Mockito.when(bookingService.getByPnr("PNR999")).thenReturn(Mono.empty());
		webTestClient.get().uri("/api/flight/ticket/PNR999").exchange().expectStatus().isNotFound();
	}

	@Test
	void getHistory_success() {
		Booking b1 = new Booking();
		b1.setPnr("PNR1");
		Booking b2 = new Booking();
		b2.setPnr("PNR2");
		Mockito.when(bookingService.getHistory("test@mail.com")).thenReturn(Flux.just(b1, b2));
		webTestClient.get().uri("/api/flight/booking/history/test@mail.com").exchange().expectStatus().isOk()
				.expectBodyList(Booking.class).hasSize(2);
	}

	@Test
	void cancelBooking_success() {
		Mockito.when(bookingService.cancelBooking("PNR123")).thenReturn(Mono.empty());
		webTestClient.delete().uri("/api/flight/booking/cancel/PNR123").exchange().expectStatus().isOk()
				.expectBody(String.class).isEqualTo("");
	}
}
