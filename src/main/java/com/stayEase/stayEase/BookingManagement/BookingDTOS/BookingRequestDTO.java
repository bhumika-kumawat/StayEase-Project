package com.stayEase.stayEase.BookingManagement.BookingDTOS;

import com.stayEase.stayEase.BookingManagement.Enum.BookingStaus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter

public class BookingRequestDTO {

    @NotNull (message = "check in time should not be null")
    private LocalDateTime checkInTime;

    @NotNull (message = " check out time should not be null")
    private LocalDateTime checkOutTime;

    @NotNull(message = "Booking status is required")
    private BookingStaus bookingStatus;

    @NotNull(message = "Room ID is required")
    @Positive(message = "Room ID must be greater than zero")
    private Long roomId;
}
