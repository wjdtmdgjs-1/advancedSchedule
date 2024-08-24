package com.sparta.upgradeschedule.dto.schedule.ResponseDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleUpdateResponseDto {
    private final Long id;
    private final String writerName;
    private final String scheduleTitle;
    private final String scheduleContents;
    private final LocalDateTime writeDate;
    private final LocalDateTime updateDate;

    public ScheduleUpdateResponseDto(Long id,
                                  String writerName,
                                  String scheduleTitle,
                                  String scheduleContents,
                                  LocalDateTime writeDate,
                                  LocalDateTime updateDate) {
        this.id = id;
        this.writerName = writerName;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContents = scheduleContents;
        this.writeDate = writeDate;
        this.updateDate = updateDate;
    }
}
