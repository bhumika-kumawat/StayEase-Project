package com.stayEase.stayEase.Hotels.HotelRepo;

import com.stayEase.stayEase.Hotels.HotelEntity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<HotelEntity , Long> {

    boolean existsByHotelEmail(String hotelEmail);
    boolean existsByhotelPhone(String phoneNo);
    List<HotelEntity> findByOwnerEntity_Id(Long ownerId);
}
