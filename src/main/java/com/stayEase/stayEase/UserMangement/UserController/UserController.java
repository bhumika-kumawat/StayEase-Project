package com.stayEase.stayEase.UserMangement.UserController;

import com.stayEase.stayEase.UserMangement.UserRequestDto.UserRequestDto;
import com.stayEase.stayEase.UserMangement.UserResponseDto.UserResponseDto;
import com.stayEase.stayEase.UserMangement.UserService.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/user")
public class UserController {

    UserService userService;

    UserController (UserService userService)
    {
        this.userService = userService;
    }


//    method to create user
    @PostMapping
    public ResponseEntity<UserResponseDto>createNewUser(@Valid  @RequestBody UserRequestDto userRequestDto)
    {
       UserResponseDto userResponseDto =  userService.createUser(userRequestDto);
       return ResponseEntity.ok(userResponseDto);

    }

//    method to update user details
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto>updateUser(@Valid @RequestBody UserRequestDto userRequestDto , @PathVariable Long userId)
    {
        UserResponseDto updatedUser = userService.updateUser(userRequestDto , userId);
        return ResponseEntity.ok(updatedUser);

    }

    //    Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(
            @PathVariable Long userId) {

        UserResponseDto user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {

        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Delete user by ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {

        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
