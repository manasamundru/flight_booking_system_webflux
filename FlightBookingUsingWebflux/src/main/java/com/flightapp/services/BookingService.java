package com.flightapp.services;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.flightapp.dto.BookingRequest;
import com.flightapp.entities.Booking;
import com.flightapp.entities.Flights;
import com.flightapp.entities.Passenger;
import com.flightapp.exceptions.ResourceNotFoundException;
import com.flightapp.repositories.BookingRepository;
import com.flightapp.util.PnrGenerator;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookingService {
    private final BookingRepository bookingRepo;
    private final FlightService flightService;
    public BookingService(BookingRepository bookingRepo,FlightService flightService) {
    	this.bookingRepo = bookingRepo;
    	this.flightService = flightService;
    }
    public Mono<String> bookTicket(String flightId, BookingRequest req) {

        return flightService.getFlightById(flightId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Flight not found")))
                .flatMap(flight -> validateAndCreateBooking(flight, req));
    }

    private Mono<String> validateAndCreateBooking(Flights flight, BookingRequest req) {
        Set<String> requestedSeats = req.getPassengers().stream()
                .map(p -> p.getSeatNumber())
                .collect(Collectors.toSet());
        if (requestedSeats.size() != req.getPassengers().size()) {
            return Mono.error(new RuntimeException("no of seats doesnt match the no of passengers"));
        }
        return bookingRepo.findByFlightId(flight.getId()).collectList()
                .flatMap(existingBookings -> {

                    Set<String> occupiedSeats = existingBookings.stream()
                            .flatMap(b -> b.getPassengers().stream())
                            .map(Passenger::getSeatNumber)
                            .collect(Collectors.toSet());

                    for (String s : requestedSeats) {
                        if (occupiedSeats.contains(s)) {
                            return Mono.error(new RuntimeException("Seat already booked: " + s));
                        }
                    }
                    int seatsToBook = req.getPassengers().size();
                    if (flight.getAvailableSeats() < seatsToBook) {
                        return Mono.error(new RuntimeException("Not enough seats available"));
                    }
                    Booking booking = new Booking();
                    booking.setPnr(PnrGenerator.generate());
                    booking.setFlightId(flight.getId());
                    booking.setAirlineId(flight.getAirlineId());
                    booking.setUserEmail(req.getUserEmail());
                    booking.setJourneyDate(req.getJourneyDate());
                    booking.setMealType(req.getMealType());
                    booking.setBookingDateTime(LocalDateTime.now());
                    booking.setStatus("BOOKED");
                    booking.setTotalSeatsBooked(seatsToBook);

                    booking.setPassengers(
                            req.getPassengers().stream()
                                    .map(p -> new Passenger(
                                            p.getName(),
                                            p.getGender(),
                                            p.getAge(),
                                            p.getSeatNumber()
                                    )).collect(Collectors.toList())
                    );
                    booking.setPricePaid(seatsToBook * flight.getPriceOneWay());
                    return flightService.decrementSeats(flight.getId(), seatsToBook)
                            .flatMap(updated -> bookingRepo.save(booking))
                            .map(saved -> saved.getPnr());
                });
    }
    
    public Mono<Booking> getByPnr(String pnr) {
        return bookingRepo.findById(pnr).
        		switchIfEmpty(Mono.error(new ResourceNotFoundException("ticket not found with pnr " + pnr)));
    }
    
    public Flux<Booking> getHistory(String email) {
        return bookingRepo.findByUserEmail(email)
        		.switchIfEmpty(Mono.error(new ResourceNotFoundException("user not found with email " + email)));
    }
    
    public Mono<Void> cancelBooking(String pnr) {
        return bookingRepo.findById(pnr)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Booking not found with pnr "+pnr)))
                .flatMap(b ->bookingRepo.delete(b)
                             .then(flightService.incrementSeats(b.getFlightId(), b.getTotalSeatsBooked()))
                        ).then();
    }
}

