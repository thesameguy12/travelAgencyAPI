package com.example.krompirica.Reservation.Dto;

import com.example.krompirica.Line.LineResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReservationResponseDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private LocalDate dateOfReservation;
    private LocalDate dateOfTravelTo;
    private LocalDate dateOfTravelFrom;
    private LineResponseDto lineId;
}
