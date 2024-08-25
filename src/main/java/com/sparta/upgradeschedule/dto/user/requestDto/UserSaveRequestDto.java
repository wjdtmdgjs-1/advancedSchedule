package com.sparta.upgradeschedule.dto.user.requestDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserSaveRequestDto {

    private String userName;
    private String email;

}
