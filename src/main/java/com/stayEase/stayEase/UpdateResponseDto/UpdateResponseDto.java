package com.stayEase.stayEase.UpdateResponseDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateResponseDto {

    String HotelName;
    String HotelAddress;
    int HotelPhone;
    String HotelEmail;
    String HotelDescription;
    String HotelCity;
    BigDecimal HotelPrice;

}
