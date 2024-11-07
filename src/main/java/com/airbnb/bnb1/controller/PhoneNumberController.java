package com.airbnb.bnb1.controller;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/phonenumbers")
public class PhoneNumberController {

    @GetMapping("/parse")
    public String parsePhoneNumber(@RequestParam String phoneNumber) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            // Parse phone number
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phoneNumber, "");

            // Return the country code as a response
            return "Country code: " + numberProto.getCountryCode();
        } catch (NumberParseException e) {
            // Handle error and return error message
            return "NumberParseException was thrown: " + e.toString();
        }
    }
}

