package com.example.krompirica.City;

import com.example.krompirica.Utils.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class CityService {
    private CityRepo repo;

    private CityEntity findOrThrow(Integer id){
        return repo.findById(id).orElseThrow(()->new NotFoundException("City with id "+id+" was not found."));
    }

    public List<CityEntity> getAllCities(){
        return repo.findAll();
    }
    public CityEntity getCityById(Integer id){
        return repo.getReferenceById(id);
    }
    public String addCity(CityEntity city){
        return repo.save(city).getName();

    }
    public CityEntity updateCity(Integer id,CityEntity city){
        findOrThrow(id);
        return repo.save(city);
    }
    public String deleteCity(Integer id){
        findOrThrow(id);
        repo.deleteById(id);
        return "Removed city with id "+id;
    }
}
