package com.flightapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flightapp.dto.BookingRequest;

import com.flightapp.services.BookingService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/flight")
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
    	this.bookingService = bookingService;
    }

    @PostMapping("/booking/{flightId}")
    public Mono<ResponseEntity<String>> bookTicket(
            @PathVariable String flightId,
            @RequestBody BookingRequest request) {

        return bookingService.bookTicket(flightId, request)
                .map(pnr -> ResponseEntity.status(HttpStatus.CREATED).body(pnr));
    }
    
}
