package com.flightapp.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightsRequest {
	@NotNull
	@NotBlank
	@JsonProperty("id")
	private String flightsRequestId;
	@NotNull
	@NotBlank
	@JsonProperty("airlineId")
    private String flightsRequestAirlineId;
	@NotNull
	@NotBlank
	@JsonProperty("fromPlace")
    private String flightsRequestFromPlace;
	@NotNull
	@NotBlank
	@JsonProperty("toPlace")
    private String flightsRequestToPlace;
	@JsonProperty("journeyDate")
    private LocalDate flightsRequestJourneyDate;
	@JsonProperty("departureTime")
    private LocalTime flightsRequestDepartureTime;
	@JsonProperty("arrivalTime")
    private LocalTime flightsRequestArrivalTime;
	@JsonProperty("roundTripAvailable")
    private boolean flightsRequestRoundTripAvailable;
	@JsonProperty("priceOneWay")
    private double flightsRequestPriceOneWay;
	@JsonProperty("priceRoundTrip")
    private double flightsRequestPriceRoundTrip;
	@JsonProperty("totalSeats")
    private int flightsRequestTotalSeats;
	@JsonProperty("mealOptions")
    private String[] flightsRequestMealOptions;
}

