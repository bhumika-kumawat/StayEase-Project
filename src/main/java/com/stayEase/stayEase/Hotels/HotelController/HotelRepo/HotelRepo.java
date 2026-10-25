package com.stayEase.stayEase.Hotels.HotelController.HotelRepo;

import com.stayEase.stayEase.Hotels.HotelController.HotelEntity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo extends JpaRepository<HotelEntity , Long> {
}
