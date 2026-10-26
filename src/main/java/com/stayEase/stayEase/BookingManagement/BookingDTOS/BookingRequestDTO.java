package com.stayEase.stayEase.BookingManagement.BookingDTOS;

import com.stayEase.stayEase.BookingManagement.Enum.BookingStaus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter

public class BookingRequestDTO {

    LocalDateTime checkInTime;
    LocalDateTime checkOutTime;
    BookingStaus bookingStatus;
    private Long roomId;
}
