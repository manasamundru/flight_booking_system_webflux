package com.flightapp.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FlightsRequest {
	@NotNull
	@NotBlank
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
    private String[] mealOptions;
}

