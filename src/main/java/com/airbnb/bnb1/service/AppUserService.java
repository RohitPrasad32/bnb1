package com.airbnb.bnb1.service;

import com.airbnb.bnb1.entity.AppUser;
import com.airbnb.bnb1.payload.LoginDto;
import org.springframework.http.ResponseEntity;

public interface AppUserService {
    public AppUser createUser(AppUser user);

    public String verifyLogin(LoginDto loginDto);
}
