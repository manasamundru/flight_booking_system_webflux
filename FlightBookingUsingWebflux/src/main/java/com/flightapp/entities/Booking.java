package com.flightapp.entities;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bookings")
public class Booking {
    @Id
    private String pnr;
    @NotNull
    @NotBlank
    private String flightId;
    @NotNull
    @NotBlank
    private String airlineId;
    @Email
    private String userEmail;
    private LocalDateTime bookingDateTime;
    @Min(1)
    private int totalSeatsBooked;
    private String mealType;
    private String status;
    private LocalDate journeyDate;
    private List<Passenger> passengers;
    private double pricePaid;
}
