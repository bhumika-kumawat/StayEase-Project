package com.stayEase.stayEase.Roles.RoleService;

import com.stayEase.stayEase.Roles.RoleRequestDto.RoleRequestDTO;
import com.stayEase.stayEase.Roles.RoleEntity.RoleEntity;
import com.stayEase.stayEase.Roles.RoleRepo.RoleRepo;

import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public RoleEntity createRole(RoleRequestDTO roleRequestDTO) {

        RoleEntity existingRole = roleRepo
                .findByRoleName(roleRequestDTO.getRoleName())
                .orElse(null);

        if (existingRole != null) {
            throw new RuntimeException("Role already exists");
        }

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName(roleRequestDTO.getRoleName());

        return roleRepo.save(roleEntity);
    }
}