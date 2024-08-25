package com.sparta.upgradeschedule.dto.schedule.RequestDto;

import lombok.Getter;

@Getter
public class ScheduleSaveRequestDto {

    private Long writerId;
    private String scheduleTitle;
    private String scheduleContents;

}
