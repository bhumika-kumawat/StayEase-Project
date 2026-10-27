package com.stayEase.stayEase.Hotels.HotelDtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HotelResponseDto {

        private String HotelName;
        private String HotelAddress;
        private String HotelPhone;
        private String HotelEmail;
        private String HotelDescription;
        private String HotelCity;
        private BigDecimal HotelPrice;
}
