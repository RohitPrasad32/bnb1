package com.airbnb.bnb1.config;

import com.airbnb.bnb1.entity.AppUser;
import com.airbnb.bnb1.repository.AppUserRepository;
import com.airbnb.bnb1.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private JWTService jwtService;

    private AppUserRepository appUserRepository;

    public JWTFilter(JWTService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token!=null && token.startsWith("Bearer ")) {
            String tokenVal = token.substring(8, token.length()-1);
           String username = jwtService.getUserName(tokenVal);
           // System.out.println(username);
            Optional<AppUser> opUser = appUserRepository.findByUsername(username);
            if(opUser.isPresent()) {
                AppUser appUser = opUser.get();

                // Below code is used to put the details in the UsernamePasswordAuthenticationToken reference variable i.e
                // auth. After that "auth.setDetails(new WebAuthenticationDetails(request));" which is used to send request to
                // allow the permission to to url to show the details of the user for which it is requesting.
                // auth.setDetails(new WebAuthenticationDetails(request)); is done in the JWTFilter.java file.
                // SecurityContextHolder.getContext().setAuthentication(auth); is done in the AppUserServiceImpl.java file.
                // SecurityContextHolder.getContext().setAuthentication(auth); is used to set the authentication object in the
                // SecurityContext which is used by Spring Security to know who the user is.


                // Below code is used to authenticate the user. It will first check the JWT token with the JWTService.
                // If the token is valid, it will return the username from the JWT token.
                // If the token is invalid, it will return null.
                // Then, it will check the username and password from the JWT token with the username and password from the database.
                // If both are valid, it will return true.
                // If the username or password is incorrect, it will return false.
                // In the AppUserServiceImpl.java file, it is used to check the username and password from the database.

                // So, when the user tries to access the url, it will first check the JWT token. If the token is valid, it will
                // then check the username and password from the JWT token with the username and password from the database.
                // If both are valid, it will allow the user to access the url.
                // If the token is invalid, it will throw a 401 Unauthorized error.
                // If the username or password is incorrect, it will throw a 403 Forbidden error.

                UsernamePasswordAuthenticationToken
                        auth = new UsernamePasswordAuthenticationToken(appUser,null, Collections.singleton(new SimpleGrantedAuthority(appUser.getRole())));
                auth.setDetails(new WebAuthenticationDetails(request));
                // here "SecurityContextHolder.getContext().setAuthentication(auth)" is used for
                // setting the authentication object in the SecurityContext.
                // This is done to allow the Spring Security to know who the user is.
                // Spring Security will use this authentication object to decide whether to allow the user to access the url or not.
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        }
        filterChain.doFilter(request,response);
        }

    }

