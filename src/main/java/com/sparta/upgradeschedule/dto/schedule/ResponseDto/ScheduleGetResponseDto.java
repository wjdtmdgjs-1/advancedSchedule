package com.sparta.upgradeschedule.dto.schedule.ResponseDto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
public class ScheduleGetResponseDto {
    private final Long id;
    private final Long writerId;
    private final String scheduleTitle;
    private final String scheduleContents;
    private final LocalDateTime writeDate;
    private final LocalDateTime updateDate;
    private final ArrayList<ArrayList<String>> picsData;
    private final ArrayList<ArrayList<String>> commentsData;

    public ScheduleGetResponseDto(Long id,
                                  Long writerId,
                                  ArrayList<ArrayList<String>> picsData,
                                  String scheduleTitle,
                                  String scheduleContents,
                                  ArrayList<ArrayList<String>> commentsData,
                                  LocalDateTime writeDate,
                                  LocalDateTime updateDate) {
        this.id = id;
        this.writerId = writerId;
        this.picsData = picsData;
        this.scheduleTitle = scheduleTitle;
        this.scheduleContents = scheduleContents;
        this.commentsData = commentsData;
        this.writeDate = writeDate;
        this.updateDate = updateDate;

    }
}
