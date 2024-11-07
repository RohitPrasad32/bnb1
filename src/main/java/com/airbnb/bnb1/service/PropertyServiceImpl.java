package com.airbnb.bnb1.service;

import com.airbnb.bnb1.entity.City;
import com.airbnb.bnb1.entity.Country;
import com.airbnb.bnb1.entity.Property;
import com.airbnb.bnb1.repository.CityRepository;
import com.airbnb.bnb1.repository.CountryRepository;
import com.airbnb.bnb1.repository.PropertyRepository;

public class PropertyServiceImpl implements PropertyService{
    private CountryRepository countryRepository;
    private CityRepository cityRepository;
    private PropertyRepository propertyRepository;

    public PropertyServiceImpl(CountryRepository countryRepository, CityRepository cityRepository, PropertyRepository propertyRepository) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Property createPropertyDetails(Property property, long countryId, long cityId) {
        Country country = countryRepository.findById(countryId).get();
        City city = cityRepository.findById(cityId).get();

        property.setCountry(country);
        property.setCity(city);

        Property save = propertyRepository.save(property);
        return save;
    }

    @Override
    public Property searchProperty(String cityName) {
        return null;
    }
}
