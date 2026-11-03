
package com.stayEase.stayEase.BookingManagement.BookingRepo;

import com.stayEase.stayEase.BookingManagement.BookingEntity.BookingEntity;
import com.stayEase.stayEase.BookingManagement.BookingStatusEnum.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepo extends JpaRepository<BookingEntity, Long> {


    @Query("""
    SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
    FROM BookingEntity b
    WHERE b.roomEntity.roomId = :roomId
      AND b.checkInDate < :requestedCheckOutDate
      AND b.checkOutDate > :requestedCheckInDate
    """)
    boolean existsDateOverlap(
            @Param("roomId") Long roomId,
            @Param("requestedCheckInDate") LocalDate requestedCheckInDate,
            @Param("requestedCheckOutDate") LocalDate requestedCheckOutDate
    );

    @Query("""
    SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
    FROM BookingEntity b
    WHERE b.roomEntity.roomId = :roomId
      AND b.checkInDate = :bookingDate
      AND b.checkOutDate = :bookingDate
      AND :requestedDate = :bookingDate
      AND b.checkInTime < :requestedCheckOutTime
      AND b.checkOutTime > :requestedCheckInTime
    """)
    boolean existsTimeOverlap(
            @Param("roomId") Long roomId,
            @Param("bookingDate") LocalDate bookingDate,
            @Param("requestedDate") LocalDate requestedDate,
            @Param("requestedCheckInTime") LocalTime requestedCheckInTime,
            @Param("requestedCheckOutTime") LocalTime requestedCheckOutTime
    );

//    get booking by room Id

    List<BookingEntity> findByRoomEntity_RoomId(Long roomId);

//    get booking by status
    List<BookingEntity> findByBookingStatus(BookingStatus status);

//    get booking by check in date
    List<BookingEntity>findByCheckInDate(LocalDate checkInDate);

//    get booking by check out date
   List<BookingEntity>findByCheckOutDate(LocalDate checkInOut);

// get all bookings by price range
    List<BookingEntity>findByTotalPriceBetween(BigDecimal minPrice , BigDecimal  maxPrice);






}
