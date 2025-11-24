package com.flightapp.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AirlineRequest {
    private String id;
	@NotNull
	@NotNull
    private String name;
}