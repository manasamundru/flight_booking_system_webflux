package com.flightapp.repositories;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.flightapp.entities.Flights;
import reactor.core.publisher.Flux;
public interface FlightRepository extends ReactiveMongoRepository<Flights, String> {
    Flux<Flights> findByFromPlaceAndToPlaceAndJourneyDate(String fromPlace, String toPlace, String journeyDate);
}
