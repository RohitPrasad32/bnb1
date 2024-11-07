package com.airbnb.bnb1.controller;

import com.airbnb.bnb1.entity.City;
import com.airbnb.bnb1.entity.Country;
import com.airbnb.bnb1.entity.Property;
import com.airbnb.bnb1.repository.CityRepository;
import com.airbnb.bnb1.repository.CountryRepository;
import com.airbnb.bnb1.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/property")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @PostMapping("create")
    public ResponseEntity<Property> createPropertyDetails(
            @RequestBody Property property,
            @RequestParam long countryId,
            @RequestParam long cityId
    ){

        Country country = countryRepository.findById(countryId).get();
        City city = cityRepository.findById(cityId).get();

        property.setCountry(country);
        property.setCity(city);

        Property save = propertyRepository.save(property);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/propertyresult")
    public List<Property> searchProperty(
            @RequestParam("city") String cityName
    ){
        return propertyRepository.searchProperty(cityName);

    }
}
