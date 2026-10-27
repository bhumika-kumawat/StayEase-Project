package com.stayEase.stayEase.Hotels.HotelDtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class HotelRequestDTO {

    @NotBlank (message = "Hotel name is mandatory") @Size (max = 100)
    private String hotelName;

    @NotBlank
    @Size(max = 200)
    private String hotelAddress;

    @NotBlank (message = "phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String hotelPhone;

    @Email
    @NotBlank (message = "email is mandatory") @Size (max =254)
    private String hotelEmail;

    @Size( max = 1000) @NotBlank
    private String hotelDescription;
    private int hotelRating;

    @NotBlank (message = "Hotel city is required") @Size (max = 150)
    private String hotelCity;

    @NotNull
    @DecimalMin(value = "0.01")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal hotelPrice;
}
