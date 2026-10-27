package com.stayEase.stayEase.Rooms.RoomDtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RoomRequestDTO {

    @Size(max = 50, message = "Room name cannot exceed 50 characters")
    @NotBlank(message = "Room name is required")
    String roomName;

    @NotBlank(message = "Room type is required")
    String roomType;

    @NotNull(message = "Price per night is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    @Digits(integer = 8, fraction = 2, message = "Price must have up to 8 integer digits and 2 decimal places")
    BigDecimal pricePerNight;

    @NotNull
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 20, message = "Capacity cannot exceed 20")
    int capacity;

    @NotBlank(message = "Room description is required")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    String description;


}
