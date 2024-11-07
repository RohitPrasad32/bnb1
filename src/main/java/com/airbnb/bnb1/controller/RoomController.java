package com.airbnb.bnb1.controller;

import com.airbnb.bnb1.entity.Property;
import com.airbnb.bnb1.entity.Room;
import com.airbnb.bnb1.repository.PropertyRepository;
import com.airbnb.bnb1.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/room")
public class RoomController {
    private RoomRepository roomRepository;
    private PropertyRepository propertyRepository;

    public RoomController(RoomRepository roomRepository, PropertyRepository propertyRepository){
        this.roomRepository = roomRepository;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room, @RequestParam long propertyId){
        Property property = propertyRepository.findById(propertyId).get();
        room.setProperty(property);

        return new ResponseEntity<>(roomRepository.save(room), HttpStatus.CREATED);
    }

}
