package com.stayEase.stayEase.Roles.RoleRepo;

import com.stayEase.stayEase.Roles.RoleEntity.RoleEntity;
import com.stayEase.stayEase.SpringSecurity.SpringSecurityEnums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(UserRole roleName);

    boolean existsByRoleName(UserRole roleName);

}