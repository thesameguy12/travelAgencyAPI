package com.example.krompirica.Reservation.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDto {

    private Integer id;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private LocalDate dateOfReservation;
    @NotNull
    private LocalDate dateOfTravelTo;

    private LocalDate dateOfTravelFrom;
    @NotNull
    private Integer lineId;
}
