package com.stayEase.stayEase.UserMangement.UserService;

import com.stayEase.stayEase.CustomExceptions.ResourceNotFoundException;
import com.stayEase.stayEase.UserMangement.UserEntity.UserEntity;
import com.stayEase.stayEase.UserMangement.UserRepo.UserRepo;
import com.stayEase.stayEase.UserMangement.UserRequestDto.UserRequestDto;
import com.stayEase.stayEase.UserMangement.UserResponseDto.UserResponseDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepo userRepo;

    UserService(UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }

//    method to create a new user
    public UserResponseDto createUser(UserRequestDto userRequestDto)
    {
        UserEntity userEntity = mapToUserEntity(userRequestDto);
        UserEntity  savedUser = userRepo.save(userEntity);
        return mapToUserDto(savedUser);

    }

//   method to update user details
    public UserResponseDto updateUser(UserRequestDto userRequestDto , Long userId)
    {
        UserEntity existingUser = userRepo.findById(userId).orElseThrow(
                () ->new ResourceNotFoundException("Invalid User")
        );

        existingUser.setPassword(userRequestDto.getPassword());
        existingUser.setPhoneNo(userRequestDto.getPhoneNo());
        existingUser.setFirstName(userRequestDto.getFirstName());
        existingUser.setLastName(userRequestDto.getLastName());
        existingUser.setEmail(userRequestDto.getEmail());

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

    public UserEntity mapToUserEntity(UserRequestDto userRequestDto)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setFirstName(userRequestDto.getFirstName());
        userEntity.setLastName(userRequestDto.getLastName());
        userEntity.setPassword(userRequestDto.getPassword());
        userEntity.setPhoneNo(userRequestDto.getPhoneNo());

        return userEntity;
    }

//    method t convert entity into dto

    public UserResponseDto mapToUserDto(UserEntity userEntity)
    {
        UserResponseDto userResponseDto =  new UserResponseDto();

        userResponseDto.setEmail(userEntity.getEmail());
        userResponseDto.setFirstName(userEntity.getFirstName());
        userResponseDto.setLastName(userEntity.getLastName());
        userEntity.setPassword(userEntity.getPassword());

        return userResponseDto;




    }







}
