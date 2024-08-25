package com.sparta.upgradeschedule.controller;

import com.sparta.upgradeschedule.dto.user.requestDto.UserSaveRequestDto;
import com.sparta.upgradeschedule.dto.user.requestDto.UserUpdateRequestDto;
import com.sparta.upgradeschedule.dto.user.responseDto.UserGetResponseDto;
import com.sparta.upgradeschedule.dto.user.responseDto.UserSaveResponseDto;
import com.sparta.upgradeschedule.dto.user.responseDto.UserUpdateResponseDto;
import com.sparta.upgradeschedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userservice;

    @PostMapping("/users")
    public ResponseEntity<UserSaveResponseDto> saveUser(@RequestBody UserSaveRequestDto userSaveRequestDto){
        return ResponseEntity.ok(userservice.saveUser(userSaveRequestDto));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserGetResponseDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userservice.getUser(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserGetResponseDto>> getUsers(){
        return ResponseEntity.ok(userservice.getUsers());
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserUpdateResponseDto> updateUser(@PathVariable Long id,
                                                            @RequestBody UserUpdateRequestDto userUpdateRequestDto){
        return ResponseEntity.ok(userservice.updateUser(id,userUpdateRequestDto));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        userservice.deleteUser(id);
    }
}
