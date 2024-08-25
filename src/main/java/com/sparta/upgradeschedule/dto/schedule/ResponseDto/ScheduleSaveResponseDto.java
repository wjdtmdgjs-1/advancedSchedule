package com.sparta.upgradeschedule.dto.schedule.ResponseDto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleSaveResponseDto {
    private final Long id;
    private final Long writerId;
    private final String scheduleTitle;
    private final String scheduleContents;
    private final LocalDateTime writeDate;
    private final LocalDateTime updateDate;

    public ScheduleSaveResponseDto(Long id,
                                   Long writerId,
                                   String scheduleTitle,
                                   String scheduleContents,
                                   LocalDateTime writeDate,
                                   LocalDateTime updateDate) {
        this.id = id;
        this.writerId = writerId;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContents = scheduleContents;
        this.writeDate = writeDate;
        this.updateDate = updateDate;
    }
}
