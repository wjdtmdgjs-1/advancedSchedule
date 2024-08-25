package com.sparta.upgradeschedule.dto.schedule.RequestDto;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class ScheduleSaveRequestDto {

    private Long writerId;
    private ArrayList<Long> picsId;
    private String scheduleTitle;
    private String scheduleContents;

}
