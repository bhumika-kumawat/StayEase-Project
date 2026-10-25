package com.stayEase.stayEase.Hotels.HotelDtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class HotelUpdateReqDto {

    String HotelAddress;
    int HotelPhone;
    String HotelDescription;
    BigDecimal HotelPrice;

}
