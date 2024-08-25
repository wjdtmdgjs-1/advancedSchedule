package com.sparta.upgradeschedule.dto.schedule.ResponseDto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ScheduleGetAllResponseDto {
    private final Long id;
    private final Long writerId;
    private final String scheduleTitle;
    private final String scheduleContents;
    private final LocalDateTime writeDate;
    private final LocalDateTime updateDate;
    private final List<Long> picsId;

    public ScheduleGetAllResponseDto(Long id,
                                     Long writerId,
                                     List<Long> picsId,
                                     String scheduleTitle,
                                     String scheduleContents,
                                     LocalDateTime writeDate,
                                     LocalDateTime updateDate) {
        this.id = id;
        this.writerId = writerId;
        this.picsId=picsId;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContents = scheduleContents;
        this.writeDate = writeDate;
        this.updateDate = updateDate;
    }
}
