package com.stayEase.stayEase.Rooms.RoomEntity;

import com.stayEase.stayEase.BookingManagement.BookingEntity.BookingEntity;
import com.stayEase.stayEase.Hotels.HotelEntity.HotelEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class RoomEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int room_id;

    String roomName;
    String roomType;
    BigDecimal pricePerNight;
    int capacity;
    String description;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    HotelEntity hotel;

    @OneToMany (mappedBy = "roomEntity")
    List<BookingEntity> bookingEntity;


}
