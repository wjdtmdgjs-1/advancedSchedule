package com.sparta.upgradeschedule.dto.user.responseDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserSaveResponseDto {

    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime userCreateDate;
    private final LocalDateTime userUpdateDate;

    public UserSaveResponseDto(Long id,
                               String userName,
                               String email,
                               LocalDateTime userCreateDate,
                               LocalDateTime userUpdateDate) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.userCreateDate = userCreateDate;
        this.userUpdateDate = userUpdateDate;
    }
}
