package com.flightapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.entities.Airline;
import com.flightapp.services.AirlineService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/airline")
public class AirlineController {
     @Autowired
     private AirlineService airlineService;
     @PostMapping()
     public Mono<ResponseEntity<String>> addAirline(@RequestBody Airline airline) {
         return airlineService.save(airline)
                 .map(id -> ResponseEntity
                         .status(HttpStatus.CREATED)
                         .body(id));
     }

}
