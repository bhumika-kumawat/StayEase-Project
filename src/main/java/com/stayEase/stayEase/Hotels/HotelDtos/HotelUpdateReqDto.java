package com.stayEase.stayEase.Hotels.HotelDtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HotelUpdateReqDto {

    @NotBlank(message = "Hotel address is mandatory")
    @Size(max = 200, message = "Hotel address cannot exceed 200 characters")
    private String hotelAddress;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$",
            message = "Phone number must be exactly 10 digits")
    private String hotelPhone;

    @NotBlank(message = "Hotel description is mandatory")
    @Size(max = 1000, message = "Hotel description cannot exceed 1000 characters")
    private String hotelDescription;

    @NotNull(message = "Hotel price is required")
    @DecimalMin(value = "0.01", message = "Hotel price must be greater than zero")
    @Digits(integer = 8, fraction = 2,
            message = "Hotel price must have up to 8 integer digits and 2 decimal places")
    private BigDecimal hotelPrice;
}