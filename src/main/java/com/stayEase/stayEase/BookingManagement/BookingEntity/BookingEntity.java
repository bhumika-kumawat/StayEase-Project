package com.stayEase.stayEase.BookingManagement.BookingEntity;

import com.stayEase.stayEase.BookingManagement.Enum.BookingStaus;
import com.stayEase.stayEase.Rooms.RoomEntity.RoomEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private BigDecimal totalPrice;
    private BookingStaus bookingStatus;

     @ManyToOne
     @JoinColumn (name = "room_id")
     RoomEntity roomEntity;




}
