package com.airbnb.bnb1.repository;

import com.airbnb.bnb1.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}