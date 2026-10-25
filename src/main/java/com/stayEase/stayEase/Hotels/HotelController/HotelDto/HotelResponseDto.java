package com.stayEase.stayEase.Hotels.HotelController.HotelDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HotelResponseDto {

        String HotelName;
        String HotelAddress;
        int HotelPhone;
        String HotelEmail;
        String HotelDescription;
        String HotelCity;
        BigDecimal HotelPrice;
}
