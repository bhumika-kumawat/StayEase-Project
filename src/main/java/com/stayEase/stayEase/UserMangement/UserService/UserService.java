package com.stayEase.stayEase.UserMangement.UserService;

import com.stayEase.stayEase.CustomExceptions.ResourceNotFoundException;
import com.stayEase.stayEase.Roles.RoleEntity.RoleEntity;
import com.stayEase.stayEase.Roles.RoleRepo.RoleRepo;
import com.stayEase.stayEase.SpringSecurity.SpringSecurityEnums.UserRole;
import com.stayEase.stayEase.UserMangement.UserEntity.UserEntity;
import com.stayEase.stayEase.UserMangement.UserRepo.UserRepo;
import com.stayEase.stayEase.UserMangement.UserRequestDto.UserRequestDto;
import com.stayEase.stayEase.UserMangement.UserResponseDto.UserResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }


    //    method to create a new user
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        UserEntity userEntity = mapToUserEntity(userRequestDto);

        RoleEntity customerRole = roleRepo.findByRoleName(UserRole.CUSTOMER)
                .orElseThrow(() -> new RuntimeException("CUSTOMER role not found"));

        userEntity.getRoles().add(customerRole);

        UserEntity savedUser = userRepo.save(userEntity);
        return mapToUserDto(savedUser);

    }

    //   method to update user details
    public UserResponseDto updateUser(UserRequestDto userRequestDto, Long userId) {
        UserEntity existingUser = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Invalid User")
        );

        existingUser.setPhoneNo(userRequestDto.getPhoneNo());
        existingUser.setFirstName(userRequestDto.getFirstName());
        existingUser.setLastName(userRequestDto.getLastName());
        existingUser.setEmail(userRequestDto.getEmail());

        if (userRequestDto.getPassword() != null
                && !userRequestDto.getPassword().isBlank()) {

            existingUser.setPassword(
                    passwordEncoder.encode(userRequestDto.getPassword())
            );
        }

        UserEntity savedUser = userRepo.save(existingUser);
        return mapToUserDto(savedUser);

    }

    //    Get user by ID
    public UserResponseDto getUserById(Long userId) {

        UserEntity existingUser = userRepo.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return mapToUserDto(existingUser);
    }

    // Get all users
    public List<UserResponseDto> getAllUsers() {

        List<UserEntity> users = userRepo.findAll();

        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    // Delete user by ID
    public void deleteUser(Long userId) {

        UserEntity existingUser = userRepo.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        userRepo.delete(existingUser);
    }

//    method to convert request dto into entity

    public UserEntity mapToUserEntity(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setFirstName(userRequestDto.getFirstName());
        userEntity.setLastName(userRequestDto.getLastName());
        userEntity.setPhoneNo(userRequestDto.getPhoneNo());

        userEntity.setPassword(
                passwordEncoder.encode(userRequestDto.getPassword())
        );

        return userEntity;
    }

//    method t convert entity into dto

    public UserResponseDto mapToUserDto(UserEntity userEntity) {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setEmail(userEntity.getEmail());
        userResponseDto.setFirstName(userEntity.getFirstName());
        userResponseDto.setLastName(userEntity.getLastName());
        userResponseDto.setPhoneNo(userEntity.getPhoneNo());
        return userResponseDto;
    }
}
