package com.stayEase.stayEase.BookingManagement.BookingDTOS;

import com.stayEase.stayEase.BookingManagement.BookingStatusEnum.BookingStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingStatusRequestDTO {

    @Enumerated(EnumType.STRING)
    @NotNull (message = "Booking status is required")
    BookingStatus bookingStatus;

}
