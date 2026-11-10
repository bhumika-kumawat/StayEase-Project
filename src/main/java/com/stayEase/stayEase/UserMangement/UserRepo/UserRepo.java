package com.stayEase.stayEase.UserMangement.UserRepo;

import com.stayEase.stayEase.UserMangement.UserEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity , Long> {

    UserEntity findByEmail (String email);
}
