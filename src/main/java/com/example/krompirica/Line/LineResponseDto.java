package com.example.krompirica.Line;

import com.example.krompirica.City.CityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineResponseDto {
    private Integer id;
    private CityDto cityIdTo;
    private CityDto cityIdFrom;
    private Integer oneWayTicketPrice;
    private Integer twoWayTicketPrice;
    private Integer time;
}
