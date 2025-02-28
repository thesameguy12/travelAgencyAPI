package com.example.krompirica.Line;

import com.example.krompirica.City.CityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineDto {
    private Integer id;
    private Integer cityIdTo;
    private Integer cityIdFrom;
    private Integer oneWayTicketPrice;
    private Integer twoWayTicketPrice;
    private Integer time;
}
