package com.airbnb.bnb1.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTToken {
    private String tokenType;
    private String token;
}
