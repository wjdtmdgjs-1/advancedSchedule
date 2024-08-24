package com.sparta.upgradeschedule.service;

import com.sparta.upgradeschedule.dto.schedule.RequestDto.ScheduleSaveRequestDto;
import com.sparta.upgradeschedule.dto.schedule.RequestDto.ScheduleUpdateRequestDto;
import com.sparta.upgradeschedule.dto.schedule.ResponseDto.ScheduleGetResponseDto;
import com.sparta.upgradeschedule.dto.schedule.ResponseDto.ScheduleSaveResponseDto;
import com.sparta.upgradeschedule.dto.schedule.ResponseDto.ScheduleUpdateResponseDto;
import com.sparta.upgradeschedule.entity.Schedule;
import com.sparta.upgradeschedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto scheduleSaveRequestDto) {
        Schedule schedule = new Schedule(
                scheduleSaveRequestDto.getWriterName(),
                scheduleSaveRequestDto.getScheduleTitle(),
                scheduleSaveRequestDto.getScheduleContents()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleSaveResponseDto(
                savedSchedule.getId(),
                savedSchedule.getWriterName(),
                savedSchedule.getScheduleTitle(),
                savedSchedule.getScheduleContents(),
                savedSchedule.getWriteDate(),
                savedSchedule.getUpdateDate()
        );
    }

    public ScheduleGetResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()->new NullPointerException("일정이 없습니다."));
        return new ScheduleGetResponseDto(
                schedule.getId(),
                schedule.getWriterName(),
                schedule.getScheduleTitle(),
                schedule.getScheduleContents(),
                schedule.getWriteDate(),
                schedule.getUpdateDate()
        );
    }

    @Transactional
    public ScheduleUpdateResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()->new NullPointerException("일정이 없습니다."));
        schedule.update(scheduleUpdateRequestDto.getScheduleTitle(),
                scheduleUpdateRequestDto.getScheduleContents());
        return new ScheduleUpdateResponseDto(
                schedule.getId(),
                schedule.getWriterName(),
                schedule.getScheduleTitle(),
                schedule.getScheduleContents(),
                schedule.getWriteDate(),
                schedule.getUpdateDate()
        );
    }
}
