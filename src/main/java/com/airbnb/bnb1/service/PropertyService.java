package com.airbnb.bnb1.service;

import com.airbnb.bnb1.entity.Property;

public interface PropertyService {
    public Property createPropertyDetails(Property property, long countryId, long cityId);
    public Property searchProperty(String cityName);
}
