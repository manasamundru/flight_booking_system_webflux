package com.flightapp.dto;

import java.time.LocalDate;
import java.util.List;

import com.flightapp.entities.Passenger;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRequest {

    private String userEmail;
    private String mealType;
    private LocalDate journeyDate;
    private List<Passenger> passengers;
}

