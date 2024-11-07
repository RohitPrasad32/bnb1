package com.airbnb.bnb1.repository;

import com.airbnb.bnb1.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}