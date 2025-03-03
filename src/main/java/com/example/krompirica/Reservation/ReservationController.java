package com.example.krompirica.Reservation;

import com.example.krompirica.Reservation.Dto.ReservationRequestDto;
import com.example.krompirica.Reservation.Dto.ReservationResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/reservation")
@AllArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)

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


    @PreAuthorize("hasAuthority('1') or hasAuthority('2')")
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

    @PutMapping("/{id}")
    public String putReservation(@PathVariable Integer id, @Valid @RequestBody ReservationRequestDto dto){
        return service.putReservation(id,convertToEntity(dto));
    }

    @DeleteMapping("/{id}")
    public String deleteReservation(@PathVariable Integer id){
        return service.deleteReservation(id);
    }

}
