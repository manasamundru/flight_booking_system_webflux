package com.flightapp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.flightapp.entities.Airline;

public interface AirlineRepository extends ReactiveMongoRepository<Airline, String> { 
	
}
