package com.stayEase.stayEase.BookingManagement.BookingDTOS;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter

public class BookingRequestDTO {

    @NotNull (message = "check in time should not be null")
    private LocalTime checkInTime;

    @NotNull (message = " check out time should not be null")
    private LocalTime checkOutTime;

    @NotNull(message = "Room ID is required")
    @Positive(message = "Room ID must be greater than zero")
    private Long roomId;

    @NotNull(message = "Check in date should not be null and it is before check out date")
    private LocalDate checkInDate;

    @NotNull(message = "Check out date should not be null also it is equal to check in date or after check in date ")
    private LocalDate checkOutDate;

}
