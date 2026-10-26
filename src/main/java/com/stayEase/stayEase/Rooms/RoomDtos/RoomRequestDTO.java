package com.stayEase.stayEase.Rooms.RoomDtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RoomRequestDTO {

    String roomName;
    String roomType;
    BigDecimal pricePerNight;
    int capacity;
    String description;


}
