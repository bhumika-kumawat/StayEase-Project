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

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private BigDecimal totalPrice;
    private BookingStaus bookingStatus;
    private String message;
    private Long bookingId;
    private Long roomId;


}
