package com.stayEase.stayEase.Hotels.HotelRepo;

import com.stayEase.stayEase.Hotels.HotelEntity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo extends JpaRepository<HotelEntity , Long> {
}
