package com.airbnb.bnb1.service;

import com.airbnb.bnb1.entity.AppUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiryTime;

    private Algorithm algorithm;

    private static final String USER_NAME="username";
    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException {
//        System.out.println(algorithm);
//        System.out.println(issuer);
//        System.out.println(expiryTime);
       algorithm = algorithm.HMAC256(algorithmKey);

    }
    public String generateToken(AppUser user){
        // Computer Engineer is unemployed
       return JWT.create().
                withClaim(USER_NAME,user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUserName(String token){
        DecodedJWT decodedJwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
       return decodedJwt.getClaim(USER_NAME).asString();
    }
}