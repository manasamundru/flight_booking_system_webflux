package com.flightapp.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class FlightSearchRequest {
    private String fromPlace;
    private String toPlace;
    private LocalDate journeyDate;
}