package com.stayEase.stayEase.Rooms.RoomEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RoomEntity {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int room_id;

    String roomName;
    String roomType;
    Double pricePerNight;
    String availableStatus;


}
