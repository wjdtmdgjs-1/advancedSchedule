package com.sparta.upgradeschedule.service;

import com.sparta.upgradeschedule.dto.user.requestDto.UserSaveRequestDto;
import com.sparta.upgradeschedule.dto.user.requestDto.UserUpdateRequestDto;
import com.sparta.upgradeschedule.dto.user.responseDto.UserGetResponseDto;
import com.sparta.upgradeschedule.dto.user.responseDto.UserSaveResponseDto;
import com.sparta.upgradeschedule.dto.user.responseDto.UserUpdateResponseDto;
import com.sparta.upgradeschedule.entity.User;
import com.sparta.upgradeschedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserSaveResponseDto saveUser(UserSaveRequestDto userSaveRequestDto) {
        User user = new User(userSaveRequestDto.getUserName(),userSaveRequestDto.getEmail());
        userRepository.save(user);
        return new UserSaveResponseDto(user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getUserCreateDate(),
                user.getUserUpdateDate());
    }

    public UserGetResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new NullPointerException("유저가 없습니다."));
        return new UserGetResponseDto(user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getUserCreateDate(),
                user.getUserUpdateDate());
    }

    public List<UserGetResponseDto> getUsers() {
        List<User> userlist = userRepository.findAll();
        List<UserGetResponseDto> dto = new ArrayList<>();
        for(User u : userlist){
            dto.add(new UserGetResponseDto(u.getId(),
                    u.getUserName(),
                    u.getEmail(),
                    u.getUserCreateDate(),
                    u.getUserUpdateDate()));
        }
        return dto;
    }

    //유저이름, 유저이메일 수정가능합니다
    @Transactional
    public UserUpdateResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new NullPointerException("유저가 없습니다."));
        user.update(userUpdateRequestDto.getUserName(),userUpdateRequestDto.getEmail());
        return new UserUpdateResponseDto(user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getUserCreateDate(),
                user.getUserUpdateDate());
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()->new NullPointerException("유저가 없습니다."));
        userRepository.delete(user);
    }
}
