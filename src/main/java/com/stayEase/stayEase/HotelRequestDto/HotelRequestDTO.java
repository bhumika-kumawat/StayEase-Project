package com.stayEase.stayEase.HotelRequestDto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class HotelRequestDTO {

    String HotelName;
    String HotelAddress;
    int HotelPhone;
    String HotelEmail;
    String HotelDescription;
    String HotelCity;
    BigDecimal HotelPrice;
}
