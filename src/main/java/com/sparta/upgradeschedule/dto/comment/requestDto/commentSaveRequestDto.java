package com.sparta.upgradeschedule.dto.comment.requestDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class commentSaveRequestDto {
    private String commentWriterName;
    private String commentContents;
}
