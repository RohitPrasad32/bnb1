package com.airbnb.bnb1.repository;

import com.airbnb.bnb1.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}