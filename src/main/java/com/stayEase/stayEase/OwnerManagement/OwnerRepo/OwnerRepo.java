package com.stayEase.stayEase.OwnerManagement.OwnerRepo;

import com.stayEase.stayEase.OwnerManagement.OwnerEntity.OwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepo extends JpaRepository<OwnerEntity , Long> {

    boolean existsByEmail(String email);
    boolean existsByPhoneNo (String phoneNo);

    boolean existsByEmailAndIdNot(String email, Long ownerId);
    boolean existsByPhoneNoAndIdNot(String phoneNo, Long ownerId);



}
