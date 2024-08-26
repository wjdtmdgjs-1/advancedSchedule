package com.sparta.upgradeschedule.dto.user.responseDto;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
        private final String token;

    public UserLoginResponseDto(String token) {
        this.token = token;
    }
}
