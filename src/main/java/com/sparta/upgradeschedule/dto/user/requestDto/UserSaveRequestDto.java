package com.sparta.upgradeschedule.dto.user.requestDto;

import lombok.Getter;

@Getter
public class UserSaveRequestDto {

    private String userName;
    private String email;
    private String password;
    private boolean admin = false;
    private String adminToken = "";

}
