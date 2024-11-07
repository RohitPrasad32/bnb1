package com.airbnb.bnb1.repository;

import com.airbnb.bnb1.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}