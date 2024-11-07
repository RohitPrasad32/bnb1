package com.airbnb.bnb1.repository;

import com.airbnb.bnb1.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

//    @Query("Select p from Property p JOIN City c on p.city=c.id where c.name=:cityName")
//    List<Property> searchProperty(
//            @Param("cityName") String cityName
//    );

    @Query("Select p from Property p INNER JOIN p.city c where c.name=:cityName")
    List<Property> searchProperty(
            @Param("cityName") String cityName
    );
}