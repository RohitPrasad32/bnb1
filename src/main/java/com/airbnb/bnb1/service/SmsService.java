package com.airbnb.bnb1.service;

import com.airbnb.bnb1.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


//import com.airbnb.bnb1.config.TwilioConfig;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SmsService {
//
//    private final TwilioConfig twilioConfig;
//
//    @Autowired
//    public SmsService(TwilioConfig twilioConfig) {
//        this.twilioConfig = twilioConfig;
//    }
//
//    public String sendSms(String toPhoneNumber, String messageBody) {
//        Message message = Message.creator(
//                new PhoneNumber(toPhoneNumber),
//                new PhoneNumber(twilioConfig.getTwilioPhoneNumber()),
//                messageBody
//        ).create();
//
//        return message.getSid(); // Return the SID of the sent message
//    }
//}

@Service
public class SmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    private final TwilioConfig twilioConfig;

    @Autowired
    public SmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public String sendSms(String toPhoneNumber, String messageBody) {
       try {
           logger.info("Starting to send SMS" + new Date());
           Message message = Message.creator(
                   new PhoneNumber(toPhoneNumber),
                   new PhoneNumber(twilioConfig.getTwilioPhoneNumber()),
                   messageBody
           ).create();
           logger.info("Send SMS" + new Date());
           return message.getSid(); // Return the SID of the sent message
       } catch (Exception e)    {
           logger.error(e.getMessage());
       }
       return "message sent";
    }
}
