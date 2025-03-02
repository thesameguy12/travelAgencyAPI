package com.example.krompirica.Reservation;

import com.example.krompirica.Line.LineEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sound.sampled.Line;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="reservation")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="firstname",nullable = false)
    private String firstname;

    @Column(name="lastname",nullable = false)
    private String lastname;

    @Column(name="DateOfReservation",nullable = false)
    private LocalDate dateOfReservation;

    @Column(name="DateOfTravelTo",nullable = false)
    private LocalDate dateOfTravelTo;

    @Column(name="DateOfTravelFrom",nullable = true)
    private LocalDate dateOfTravelFrom;
    @ManyToOne
    @JoinColumn(name="lineId",referencedColumnName = "id",nullable = false)
    private LineEntity lineId;
}
