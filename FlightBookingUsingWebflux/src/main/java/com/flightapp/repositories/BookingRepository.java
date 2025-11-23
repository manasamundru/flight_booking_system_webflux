package com.flightapp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.flightapp.entities.Booking;
import reactor.core.publisher.Flux;

public interface BookingRepository extends ReactiveMongoRepository<Booking, String> {
    Flux<Booking> findByUserEmail(String email);
    Flux<Booking> findByFlightId(String flightId);
}
