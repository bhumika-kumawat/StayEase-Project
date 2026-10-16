package com.stayEase.stayEase.HotelRepo;

import com.stayEase.stayEase.HotelEntity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepo extends JpaRepository<HotelEntity , Long> {
}
