package com.example.krompirica.City;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {

    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String country;
    @NotNull
    private String station;

}
