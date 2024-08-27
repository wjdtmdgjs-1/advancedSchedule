package com.sparta.upgradeschedule.dto.comment.responseDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentUpdateResponseDto {
    private final Long id;
    private final String commentWriterName;
    private final String commentContents;
    private final LocalDateTime commentWriteDate;
    private final LocalDateTime commentUpdateDate;

    public CommentUpdateResponseDto(Long id,
                                    String commentWriterName,
                                    String commentContents,
                                    LocalDateTime commentWriteDate,
                                    LocalDateTime commentUpdateDate) {
        this.id = id;
        this.commentWriterName = commentWriterName;
        this.commentContents = commentContents;
        this.commentWriteDate = commentWriteDate;
        this.commentUpdateDate = commentUpdateDate;
    }
}
