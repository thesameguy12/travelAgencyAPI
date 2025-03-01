package com.example.krompirica.Line;

import com.example.krompirica.City.CityDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineRequestDto {
    private Integer id;
    @NotNull(message = "cityIdTo is required")
    private Integer cityIdTo;
    @NotNull(message = "cityIdFrom is required")
    private Integer cityIdFrom;
    @NotNull(message = "oneWayTicketPrice is required")
    private Integer oneWayTicketPrice;
    @NotNull(message = "twoWayTicketPrice is required")
    private Integer twoWayTicketPrice;
    @NotNull(message = "time is required")
    private Integer time;
}
