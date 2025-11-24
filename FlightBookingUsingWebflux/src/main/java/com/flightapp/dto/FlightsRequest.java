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
	private String FlightsRequestId;
	@NotNull
	@NotBlank
    private String FlightsRequestAirlineId;
	@NotNull
	@NotBlank
    private String FlightsRequestFromPlace;
	@NotNull
	@NotBlank
    private String FlightsRequestToPlace;
    private LocalDate FlightsRequestJourneyDate;
    private LocalTime FlightsRequestDepartureTime;
    private LocalTime FlightsRequestArrivalTime;
    private boolean FlightsRequestRoundTripAvailable;
    private double FlightsRequestPriceOneWay;
    private double FlightsRequestPriceRoundTrip;
    private int FlightsRequestTotalSeats;
    private String[] FlightsRequestMealOptions;
}

