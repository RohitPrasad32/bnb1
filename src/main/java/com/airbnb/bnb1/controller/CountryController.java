package com.airbnb.bnb1.controller;

import com.airbnb.bnb1.entity.Country;
import com.airbnb.bnb1.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/country")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @PostMapping("/create")
    public ResponseEntity<Country> createCountryDetails(@RequestBody Country country, @RequestParam long cityId, @RequestParam long countryId){
        Country save = countryRepository.save(country);
        return new ResponseEntity<>(save, HttpStatus.CREATED);

    }
}
