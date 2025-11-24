package com.flightapp.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirlineRequest {
	@NotNull
	@NotBlank
	@JsonProperty("id")
    private String airlineRequestId;
	@NotNull
	@NotBlank
	@JsonProperty("name")
    private String airlineRequestName;
}