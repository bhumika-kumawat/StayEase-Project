package com.stayEase.stayEase.UpdateReqDto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Setter
@Getter
public class UpdateReqDto {

    String HotelAddress;
    int HotelPhone;
    String HotelDescription;
    BigDecimal HotelPrice;

}
