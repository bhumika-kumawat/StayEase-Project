package com.stayEase.stayEase.Rooms.RoomDtos;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class RoomUpdateResponseDTO {

    private String roomName;
    private String roomType;
    private BigDecimal pricePerNight;
    private int capacity;
    private String description;


}
