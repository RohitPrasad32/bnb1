package com.airbnb.bnb1.controller;

import com.airbnb.bnb1.entity.AppUser;
import com.airbnb.bnb1.entity.Booking;
import com.airbnb.bnb1.entity.Property;
import com.airbnb.bnb1.entity.Room;
import com.airbnb.bnb1.repository.BookingRepository;
import com.airbnb.bnb1.repository.PropertyRepository;
import com.airbnb.bnb1.repository.RoomRepository;
import com.airbnb.bnb1.service.PDFService;
import com.airbnb.bnb1.service.SmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController {
    private RoomRepository roomRepository;
    private PropertyRepository propertyRepository;
    private BookingRepository bookingRepository;
    private PDFService pdfService;
    private SmsService smsService;

    public BookingController(RoomRepository roomRepository, PropertyRepository propertyRepository, BookingRepository bookingRepository, PDFService pdfService, SmsService smsService) {
        this.roomRepository = roomRepository;
        this.propertyRepository = propertyRepository;
        this.bookingRepository = bookingRepository;
        this.pdfService = pdfService;
        this.smsService = smsService;
    }

    // Commented to make notes of below code date : 28/08/2024
//    @PostMapping("/createBooking")
//    public ResponseEntity<?> createBooking(
//            @RequestParam long propertyId,
//            @RequestParam String roomType,
//
//    ){
//        Room room = roomRepository.findByPropertyIdAndType(propertyId, roomType);
//        if(room.getCount()==0) {
//            return new ResponseEntity<>("No rooms available", HttpStatus.INTERNAL_SERVER_ERROR);
//        }else {
//            int val = room.getCount();
//            room.setCount(val-1);
//            roomRepository.save(room);
//            return new ResponseEntity<>("Room Booked", HttpStatus.CREATED);
//
//        }
//    }
@PostMapping("/createBooking")
public ResponseEntity<?> createBooking(
        @RequestParam long propertyId,
        @RequestParam String roomType,
        @RequestBody Booking booking,
        @AuthenticationPrincipal AppUser user
        ){
    Property property = propertyRepository.findById(propertyId).get();
    List<LocalDate> datesBetween = getDatesBetween(booking.getCheckInDate(), booking.getCheckOutDate());
    List<Room> rooms = new ArrayList<>();

    for(LocalDate date: datesBetween){
        Room room = roomRepository.findByPropertyIdRoomtypeAndDate(propertyId, roomType,date);
        if(room.getCount()==0) {
           // rooms.removeAll(rooms);
            return new ResponseEntity<>("No rooms available", HttpStatus.INTERNAL_SERVER_ERROR);
        }

//        else {
//            float nightlyPrice = room.getPrice();
//            float totalPrice = nightlyPrice*booking.getTotalPrice();
//            booking.setTotalPrice(totalPrice);
//            booking.setProperty(property);
//            booking.setAppUser(user);
//            booking.setTypeOfRoom(roomType);
//
//            Booking savedBooking = bookingRepository.save(booking);
//            if(savedBooking != null) {
//                int val = room.getCount();
//                room.setCount(val - 1);
//                roomRepository.save(room);
//            }
//            return new ResponseEntity<>("Room Booked", HttpStatus.CREATED);
//
//        }
        rooms.add(room);

    }
    //Booking
    float total = 0;
    for(Room room : rooms) {
        total = total + room.getPrice();
    }
    booking.setTotalPrice(total);
    booking.setProperty(property);
    booking.setAppUser(user);
    booking.setTypeOfRoom(roomType);
    Booking savedBooking = bookingRepository.save(booking);

    if(savedBooking != null) {
        for (Room room : rooms) {
            int availableRooms = room.getCount();
            room.setCount(availableRooms - 1);
            roomRepository.save(room);
        }
    }

    // Generate pdf document
    pdfService.generatePdf(savedBooking);

    //Send SMS Conformation
//    smsService.sendSms(booking.getMobile(), "Your booking for "+savedBooking.getProperty().getName()
//    +" has been confirmed. Booking ID: "+booking.getId());

    smsService.sendSms("+91"+booking.getMobile(), "Your booking is confirmed. Booking ID: "+booking.getId());
   // smsService.sendSms("+91"+booking.getMobile(), "Kuch toa reply kar ");


    return new ResponseEntity<>(savedBooking,HttpStatus.CREATED);
}
public static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate){
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = startDate;

        while(!currentDate.isAfter(endDate)){
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return dates;
}
}
