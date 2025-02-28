package com.example.krompirica.City;

import com.example.krompirica.Utils.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/city")
public class CityController {
    private final CityService service;
    private final ModelMapper mapper;
    private CityEntity convertToEntity(CityDto dto){return mapper.map(dto,CityEntity.class);}
    private CityDto convertToDto(CityEntity entity){return mapper.map(entity,CityDto.class);}

    @PostMapping
    public String postCity(@Valid @RequestBody CityDto dto){
        return service.addCity(convertToEntity(dto));
    }

    @GetMapping
    public List<CityDto> getCity(){
        var cityList= StreamSupport.stream(service.getAllCities().spliterator(),false).collect(Collectors.toList());
        return cityList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public CityDto getOneCity(@PathVariable Integer id){
        return convertToDto(service.getCityById(id));
    }

    @PutMapping("/{id}")
    public CityDto putCity(@PathVariable Integer id,@Valid @RequestBody CityDto dto){
        if(!id.equals(dto.getId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"A city by the id "+id+" was not found.");
        return convertToDto(service.updateCity(id,convertToEntity(dto)));
    }

    @DeleteMapping("/{id}")
    public String deleteCity(@PathVariable Integer id){
        return service.deleteCity(id);
    }
}
