package com.stayEase.stayEase.Hotels.HotelEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class HotelEntity {

    String HotelName;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long HotelId;

    String HotelAddress;
    int HotelPhone;
    String HotelEmail;
    String HotelDescription;
    int HotelRating;
    String HotelCity;
    BigDecimal HotelPrice;

}
