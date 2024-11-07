package com.airbnb.bnb1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/dummy")
public class DummyController {
    @GetMapping("/getMessage")
    public String getMessage() {
        return "Hello, this is a dummy API!";
    }
}

