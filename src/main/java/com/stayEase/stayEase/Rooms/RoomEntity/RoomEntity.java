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
    private int room_id;

    private String roomName;
    private String roomType;
    private BigDecimal pricePerNight;
    private int capacity;
    private String description;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    HotelEntity hotel;

    @OneToMany (mappedBy = "roomEntity")
    List<BookingEntity> bookingEntity;


}
