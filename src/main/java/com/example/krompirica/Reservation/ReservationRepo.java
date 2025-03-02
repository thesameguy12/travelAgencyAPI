package com.example.krompirica.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepo extends JpaRepository<ReservationEntity,Integer> {
    @Query("SELECT r FROM ReservationEntity r WHERE r.dateOfTravelTo=:dateOfTravel OR r.dateOfTravelFrom=:dateOfTravel")
    List<ReservationEntity> getReservationByTravelDate(LocalDate dateOfTravel);
}
