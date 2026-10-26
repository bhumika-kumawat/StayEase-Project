package com.stayEase.stayEase.BookingManagement.BookingEntity;

import com.stayEase.stayEase.BookingManagement.Enum.BookingStaus;
import com.stayEase.stayEase.Rooms.RoomEntity.RoomEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter


public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bookingId;

    LocalDateTime checkInTime;
    LocalDateTime checkOutTime;
    BigDecimal totalPrice;
    BookingStaus bookingStatus;

     @ManyToOne
     @JoinColumn (name = "room_id")
     RoomEntity roomEntity;




}
