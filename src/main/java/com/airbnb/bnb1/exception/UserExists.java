package com.airbnb.bnb1.exception;

public class UserExists extends RuntimeException{
   public UserExists(String msg){
        super(msg);
    }
}
