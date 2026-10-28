package com.stayEase.stayEase.BookingManagement.BookingDTOS;

import com.stayEase.stayEase.BookingManagement.Enum.BookingStaus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
public class BookingResponseDTO {

    LocalDate checkInDate;
    LocalDate checkOutDate;
    LocalTime checkInTime;
    LocalTime checkOutTime;
    BigDecimal totalPrice;
    BookingStaus bookingStatus;
    String message;
    private Long bookingId;

}
