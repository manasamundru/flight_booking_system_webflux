package com.flightapp.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AirlineRequest {
	@NotNull
	@NotBlank
    private String AirlineRequestId;
	@NotNull
	@NotBlank
    private String AirlineRequestName;
}