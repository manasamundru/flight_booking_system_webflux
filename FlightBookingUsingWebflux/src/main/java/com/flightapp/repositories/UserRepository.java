package com.flightapp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.flightapp.entities.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByEmail(String email);
}
