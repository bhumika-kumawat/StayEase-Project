package com.stayEase.stayEase.BookingManagement.BookingRepo;

import com.stayEase.stayEase.BookingManagement.BookingEntity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository <BookingEntity , Long> {
}
