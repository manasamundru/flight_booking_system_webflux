package com.flightapp.entities;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    private String name;
    private String gender;
    private int age;
    private String seatNumber;
}
