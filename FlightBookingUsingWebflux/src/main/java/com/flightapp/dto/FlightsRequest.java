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
	private String flightsRequestId;
	@NotNull
	@NotBlank
    private String flightsRequestAirlineId;
	@NotNull
	@NotBlank
    private String flightsRequestFromPlace;
	@NotNull
	@NotBlank
    private String flightsRequestToPlace;
    private LocalDate flightsRequestJourneyDate;
    private LocalTime flightsRequestDepartureTime;
    private LocalTime flightsRequestArrivalTime;
    private boolean flightsRequestRoundTripAvailable;
    private double flightsRequestPriceOneWay;
    private double flightsRequestPriceRoundTrip;
    private int flightsRequestTotalSeats;
    private String[] flightsRequestMealOptions;
}

