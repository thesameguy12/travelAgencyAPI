package com.example.krompirica.Reservation;

import com.example.krompirica.Reservation.Dto.ReservationRequestDto;
import com.example.krompirica.Reservation.Dto.ReservationResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/reservation")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService service;
    private final ModelMapper mapper;

    private ReservationEntity convertToEntity(ReservationRequestDto dto){return mapper.map(dto,ReservationEntity.class);}
    private ReservationEntity convertToEntity(ReservationResponseDto dto){return mapper.map(dto,ReservationEntity.class);}
    private ReservationRequestDto convertToRequestDto(ReservationEntity entity){return mapper.map(entity,ReservationRequestDto.class);}
    private ReservationResponseDto convertToResponseDto(ReservationEntity entity){return mapper.map(entity,ReservationResponseDto.class);}

    @GetMapping
    public List<ReservationResponseDto> getAllReservations(){
        var reservations= StreamSupport.stream(service.getAllReservations().spliterator(),false).collect(Collectors.toList());
        return reservations.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }
    @GetMapping("/byDate")
    public List<ReservationResponseDto> getReservationByDate(@RequestParam(name="date") LocalDate date){
        var reservations= StreamSupport.stream(service.getReservationByDate(date).spliterator(),false).collect(Collectors.toList());
        return reservations.stream().map(this::convertToResponseDto).collect(Collectors.toList());
    }

    @PostMapping
    public String postReservation(@Valid @RequestBody ReservationRequestDto dto){
        System.out.println(convertToEntity(dto));
        return service.postReservation(convertToEntity(dto));
    }

}
