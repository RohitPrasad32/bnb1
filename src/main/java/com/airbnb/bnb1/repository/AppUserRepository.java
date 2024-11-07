package com.airbnb.bnb1.repository;

import com.airbnb.bnb1.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String Username);
    Optional<AppUser> findByEmailOrUsername(String email,String Username);

}