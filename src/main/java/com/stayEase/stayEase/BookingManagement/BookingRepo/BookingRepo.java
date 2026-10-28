//package com.stayEase.stayEase.BookingManagement.BookingRepo;
//
//import com.stayEase.stayEase.BookingManagement.BookingEntity.BookingEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//
//@Repository
//public interface BookingRepo extends JpaRepository <BookingEntity , Long> {
//
//    // 1. whether a booking exists for a room
//    boolean existsByRoomId(Long roomId);
//
//    // 2. Check whether a booking starts before a given date
//    boolean existsByRoomIdAndCheckInDateLessThan(
//            Long roomId, LocalDate date);
//
//    // 3. Check whether a booking ends after a given date
//    boolean existsByRoomIdAndCheckOutDateGreaterThan(
//            Long roomId, LocalDate date);
//
//   //  4. exists overlapping booking
//
//    boolean existsOverlappingBooking(
//            Long roomId,
//            LocalDate checkOutDate,
//            LocalDate checkInDate
//    );
//
//    List<BookingEntity>
//    findByRoomEntity_RoomIdAndCheckInTimeLessThanAndCheckOutTimeGreaterThan(
//            Long roomId,
//            LocalTime checkOutTime,
//            LocalTime checkInTime
//    );
//
//
//
//}


package com.stayEase.stayEase.BookingManagement.BookingRepo;

import com.stayEase.stayEase.BookingManagement.BookingEntity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookingRepo extends JpaRepository<BookingEntity, Long> {

    // Check whether a booking overlaps the requested dates for a room
    boolean existsByRoomEntity_RoomIdAndCheckInDateLessThanAndCheckOutDateGreaterThan(
            Long roomId,
            LocalDate checkOutDate,
            LocalDate checkInDate
    );
}

//
//
