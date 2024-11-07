package com.airbnb.bnb1.service;

import com.airbnb.bnb1.entity.AppUser;
import com.airbnb.bnb1.exception.UserExists;
import com.airbnb.bnb1.payload.LoginDto;
import com.airbnb.bnb1.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AppUserServiceImpl implements AppUserService {

// Here in below code spring Boot is unable to do Dependency injection for PasswordEncoder because passwordEncoder comes
// from spring security framework and not from Spring Boot Framework. If it would be from same library i,e spring boot
// framework, then spring boot would know which object to create. So to resolve this problem, so that dependency injection
// of PasswordEncoder (which is of Spring Security framework) can take place we have to create a method in Application
// class (i,e BnbApplication annotated with @SpringBootApplication) i.e annotated with @Bean(Which is show in BnbApplication
// class).
    private AppUserRepository appUserRepository;
    private JWTService jwtService;
    public AppUserServiceImpl(AppUserRepository appUserRepository, JWTService jwtService){
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }
    @Override
    public AppUser createUser(AppUser user) {
//        Optional<AppUser> opEmail = appUserRepository.findByEmail(user.getEmail());
//        if (opEmail.isPresent()) {
//            throw new UserExists("Email Id Exists");
//        }
//        Optional<AppUser> opUsername = appUserRepository.findByUsername(user.getName());
//        if (opUsername.isPresent()) {
//            throw new UserExists("Username Exists");
//        }


//  Below code is used for encryption of the password to make the password secure. Below in parameter
//  BCrypt.gensalt(10) is used to encrypt the password 10 times. The number that you give in input of
//  BCrypt.gensalt(), (which is 10 in below case) is the number of times the encryption will take place.
//  So we should not give very large numbers as input to BCrypt.gensalt() because if the number will be
//  larger than then it will take longer time for decryption of the password.
        String hashpw = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10));
        user.setPassword(hashpw);
        AppUser savedUser = appUserRepository.save(user);
        return savedUser;
    }

    @Override
    public String verifyLogin(LoginDto loginDto) {
            //Optional<AppUser> opUser = appUserRepository.findByUsername(loginDto.getUsername());
        Optional<AppUser> opUser = appUserRepository.findByEmailOrUsername(loginDto.getUsername(),loginDto.getUsername());
        if(opUser.isPresent()){
            AppUser appUser = opUser.get();
        // Below method compares the password from the user i,e loginDto.getPassword() and appUerr.getPassword()
        // and return true or false depending on the password.
           if(BCrypt.checkpw(loginDto.getPassword(),appUser.getPassword())){
               return jwtService.generateToken(appUser);
            }
        }
        return null;
    }
}
