package com.stayEase.stayEase.Rooms.RoomRepo;

import com.stayEase.stayEase.Rooms.RoomEntity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<RoomEntity , Long> {

}
