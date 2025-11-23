package com.flightapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flightapp.entities.Airline;
import com.flightapp.repositories.AirlineRepository;
import reactor.core.publisher.Mono;

@Service
public class AirlineService {
	@Autowired
	private AirlineRepository repo;
	public Mono<String> save(Airline a) {
	    return repo.save(a).map(saved -> saved.getId());
	}
	public Mono<Airline> findById(String id) {
		return repo.findById(id);
	}
}
