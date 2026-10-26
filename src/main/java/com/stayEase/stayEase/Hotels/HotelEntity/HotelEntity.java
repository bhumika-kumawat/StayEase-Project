package com.stayEase.stayEase.Hotels.HotelEntity;

import com.stayEase.stayEase.Rooms.RoomEntity.RoomEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "hotel")
    List <RoomEntity> roomEntity = new ArrayList<>();

}
