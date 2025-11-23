package com.flightapp.services;

import org.springframework.stereotype.Service;

import com.flightapp.dto.FlightSearchRequest;
import com.flightapp.entities.Flights;
import com.flightapp.repositories.AirlineRepository;
import com.flightapp.repositories.FlightRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FlightService {
	private final FlightRepository flightrepo;
	private final AirlineRepository airlineRepo;
	public FlightService(FlightRepository flightrepo,AirlineRepository airlineRepo) {
		this.airlineRepo = airlineRepo;
		this.flightrepo = flightrepo;
	}
	public Mono<String> addInventory(Flights flight) {
		return airlineRepo.findById(flight.getAirlineId())
				.switchIfEmpty(Mono.error(new RuntimeException("Airline not found: " + flight.getAirlineId())))
				.flatMap(a -> flightrepo.save(flight)).map(saved -> saved.getId());
	}
	public Flux<Flights> searchFlights(FlightSearchRequest req) {
        return flightrepo.findByFromPlaceAndToPlaceAndJourneyDate(req.getFromPlace(),req.getToPlace(),req.getJourneyDate());
    }
	public Mono<Flights> getFlightById(String flightId) {
	    return flightrepo.findById(flightId)
	            .switchIfEmpty(Mono.error(new RuntimeException("Flight not found: " + flightId)));
	}
	public Mono<Flights> decrementSeats(String flightId, int seatsToBook) {

        return getFlightById(flightId).flatMap(flight -> {
            if (flight.getAvailableSeats() < seatsToBook) {
                return Mono.error(new RuntimeException("Not enough seats available"));
            }
            flight.setAvailableSeats(flight.getAvailableSeats() - seatsToBook);
            return flightrepo.save(flight);
        });
    }
	public Mono<Flights> incrementSeats(String flightId, int seatsToAdd) {

        return getFlightById(flightId).flatMap(flight -> {
            flight.setAvailableSeats(flight.getAvailableSeats() + seatsToAdd);
            if (flight.getAvailableSeats() > flight.getTotalSeats()) {
                flight.setAvailableSeats(flight.getTotalSeats());
            }
            return flightrepo.save(flight);
        });
    }

}
