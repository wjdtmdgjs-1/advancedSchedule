package com.sparta.upgradeschedule.dto.comment.requestDto;

import lombok.Getter;

@Getter
public class CommentSaveRequestDto {
    private Long scheduleId;
    private String commentWriterName;
    private String commentContents;
}
