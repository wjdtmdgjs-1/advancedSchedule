package com.sparta.upgradeschedule.dto.schedule.ResponseDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulePageResponseDto {
    //할일제목, 할일 내용, 댓글개수,일정작성유저명,일정작성일,일정수정일
    private final String scheduleTitle;
    private final String scheduleContents;
    private final int Commentcount;
    private final String writerName;
    private final LocalDateTime writeDate;
    private final LocalDateTime updateDate;

    public SchedulePageResponseDto(String scheduleTitle,
                                   String scheduleContents,
                                   int Commentcount,
                                   String writerName,
                                   LocalDateTime writeDate,
                                   LocalDateTime updateDate) {
        this.scheduleTitle = scheduleTitle;
        this.scheduleContents = scheduleContents;
        this.Commentcount = Commentcount;
        this.writerName = writerName;
        this.writeDate = writeDate;
        this.updateDate = updateDate;
    }
}
