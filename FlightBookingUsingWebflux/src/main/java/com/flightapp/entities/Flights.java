package com.flightapp.entities;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Document(collection = "flight_inventory")
@Data
@Builder 
@NoArgsConstructor 
@AllArgsConstructor
public class Flights {

    @Id
    private String id;
    @NotNull
    @NotBlank
    private String airlineId;
    @NotNull
    @NotBlank
    private String fromPlace;
    @NotNull
    @NotBlank
    private String toPlace;
    private LocalDate journeyDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private boolean roundTripAvailable;
    private double priceOneWay;
    private double priceRoundTrip;
    private int totalSeats;
    private int availableSeats;
    private String[] mealOptions;
}


