package com.example.krompirica.Reservation;

import com.example.krompirica.Utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationService {
    private final ReservationRepo repo;

    private ReservationEntity findOrThrow(Integer id){
        return repo.findById(id).orElseThrow(()->new NotFoundException("Reservation with the id "+id+" was not found"));
    }

    public List<ReservationEntity> getAllReservations(){
        return repo.findAll();
    }
    public ReservationEntity getReservationById(Integer id){
        findOrThrow(id);
        return repo.getReferenceById(id);
    }

    public List<ReservationEntity> getReservationByDate(LocalDate date){
        return repo.getReservationByTravelDate(date);
    }

    public String postReservation(ReservationEntity entity){
        repo.save(entity);
        return "Added reservation.";
    }
    public String putReservation(Integer id,ReservationEntity entity){
        findOrThrow(id);
        repo.save(entity);
        return "Altered reservation with id "+id;
    }
    public String deleteReservation(Integer id){
        findOrThrow(id);
        repo.deleteById(id);
        return "Deleted reservation with id "+id;
    }

}
