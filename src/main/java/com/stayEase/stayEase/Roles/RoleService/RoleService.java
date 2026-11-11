
package com.stayEase.stayEase.Roles.RoleService;

import com.stayEase.stayEase.CustomExceptions.ResourceNotFoundException;
import com.stayEase.stayEase.Roles.RoleRequestDto.RoleRequestDTO;
import com.stayEase.stayEase.Roles.RoleEntity.RoleEntity;
import com.stayEase.stayEase.Roles.RoleRepo.RoleRepo;
import com.stayEase.stayEase.UserMangement.UserEntity.UserEntity;
import com.stayEase.stayEase.UserMangement.UserRepo.UserRepo;
import com.stayEase.stayEase.SpringSecurity.SpringSecurityEnums.UserRole;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    private final RoleRepo roleRepo;
    private final UserRepo userRepo;

    public RoleService(RoleRepo roleRepo, UserRepo userRepo) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
    }

//    role create method

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

//    get role by id

    public RoleEntity getRoleId(Long roleId) {

        return roleRepo.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role id not exist")
        );
    }

    // Assign role to an existing user
    @Transactional
    public UserEntity assignRoleToUser(String email, UserRole roleName) {

        // Find user by email
        UserEntity user = userRepo.findByEmail(email);

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        // Find role by role name
        RoleEntity role = roleRepo.findByRoleName(roleName)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Role not found")
                );

        // Add role to user's existing roles
        user.getRoles().add(role);

        // Save updated user
        return userRepo.save(user);
    }
}
