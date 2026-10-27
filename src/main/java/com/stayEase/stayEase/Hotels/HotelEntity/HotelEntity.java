package com.stayEase.stayEase.Hotels.HotelEntity;

import com.stayEase.stayEase.Rooms.RoomEntity.RoomEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class HotelEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long hotelId;

    private String hotelName;

    private String hotelAddress;

    private String hotelPhone;

    private String hotelEmail;

    private String hotelDescription;

    private int hotelRating;

    private String hotelCity;

    private BigDecimal hotelPrice;

    @OneToMany(mappedBy = "hotel")
    List <RoomEntity> roomEntity = new ArrayList<>();

}
