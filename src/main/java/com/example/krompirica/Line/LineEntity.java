package com.example.krompirica.Line;

import com.example.krompirica.City.CityEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "line")
@AllArgsConstructor
@NoArgsConstructor
public class LineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "city_id_to", referencedColumnName = "id",nullable = false)
    private CityEntity cityIdTo;

    @ManyToOne
    @JoinColumn(name="city_id_from",referencedColumnName = "id",nullable = false)
    private CityEntity cityIdFrom;

    @Column(name = "oneWayTicketPrice", nullable = false)
    private Integer oneWayTicketPrice;

    @Column(name = "twoWayTicketPrice", nullable = false)
    private Integer twoWayTicketPrice;

    @Column(nullable = false)
    private Integer time;
}
