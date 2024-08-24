package com.sparta.upgradeschedule.controller;

import com.sparta.upgradeschedule.dto.schedule.RequestDto.ScheduleSaveRequestDto;
import com.sparta.upgradeschedule.dto.schedule.RequestDto.ScheduleUpdateRequestDto;
import com.sparta.upgradeschedule.dto.schedule.ResponseDto.ScheduleGetResponseDto;
import com.sparta.upgradeschedule.dto.schedule.ResponseDto.ScheduleSaveResponseDto;
import com.sparta.upgradeschedule.dto.schedule.ResponseDto.ScheduleUpdateResponseDto;
import com.sparta.upgradeschedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleSaveResponseDto> saveSchedule(@RequestBody ScheduleSaveRequestDto scheduleSaveRequestDto){
        return ResponseEntity.ok(scheduleService.saveSchedule(scheduleSaveRequestDto));
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleGetResponseDto> getSchedule(@PathVariable Long id){
        return ResponseEntity.ok(scheduleService.getSchedule(id));
    }

    //수정은 작성자의 이름은 건들일수 없다.
    //할일 제목과, 할일 내용만 수정할 수 있다.
    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleUpdateResponseDto> updateSchedule(@PathVariable Long id,
                                                                    @RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto){
        return ResponseEntity.ok(scheduleService.updateSchedule(id,scheduleUpdateRequestDto));
    }

}
