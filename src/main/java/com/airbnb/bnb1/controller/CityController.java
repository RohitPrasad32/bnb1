package com.airbnb.bnb1.controller;

import com.airbnb.bnb1.entity.City;
import com.airbnb.bnb1.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/city")
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    @PostMapping("/create")
    public ResponseEntity<City> createCityDetail(@RequestBody City city){
        City save = cityRepository.save(city);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

}
