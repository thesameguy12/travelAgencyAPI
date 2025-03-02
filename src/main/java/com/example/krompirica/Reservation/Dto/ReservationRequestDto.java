package com.example.krompirica.Reservation.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfReservation;
    private LocalDate dateOfTravelTo;
    private LocalDate dateOfTravelFrom;
    private Integer lineId;
}
