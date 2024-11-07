package com.airbnb.bnb1.repository;

import com.airbnb.bnb1.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {


//    @Query("SELECT r FROM Room r WHERE r.property.id = :propertyId AND r.roomType = :roomType")
//    Room findByPropertyIdAndType(@Param("propertyId") Long propertyId, @Param("roomType") String roomType);

//@Query("SELECT r FROM Room r WHERE r.property.id = :propertyId AND r.type = :roomType")
//Room findByPropertyIdAndType(@Param("propertyId") Long propertyId, @Param("roomType")String roomType);

    @Query("SELECT r FROM Room r WHERE r.property.id = :propertyId AND r.type = :roomtype AND r.date = :date")
    Room findByPropertyIdRoomtypeAndDate(@Param("propertyId") Long propertyId,
                                               @Param("roomtype") String roomtype,
                                               @Param("date") LocalDate date);
}