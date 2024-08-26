package com.sparta.upgradeschedule.service;

import com.sparta.upgradeschedule.dto.schedule.RequestDto.ScheduleSaveRequestDto;
import com.sparta.upgradeschedule.dto.schedule.RequestDto.ScheduleUpdateRequestDto;
import com.sparta.upgradeschedule.dto.schedule.ResponseDto.*;
import com.sparta.upgradeschedule.entity.Comment;
import com.sparta.upgradeschedule.entity.Pic;
import com.sparta.upgradeschedule.entity.Schedule;
import com.sparta.upgradeschedule.entity.User;
import com.sparta.upgradeschedule.repository.PicRepository;
import com.sparta.upgradeschedule.repository.ScheduleRepository;
import com.sparta.upgradeschedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final PicRepository picRepository;


    @Transactional
    public ScheduleSaveResponseDto saveSchedule(ScheduleSaveRequestDto scheduleSaveRequestDto) {
        //writer id를 가진 유저가 있는지 체크하기
        userRepository.findById(scheduleSaveRequestDto.getWriterId());
        Schedule schedule = new Schedule(
                scheduleSaveRequestDto.getWriterId(),
                scheduleSaveRequestDto.getScheduleTitle(),
                scheduleSaveRequestDto.getScheduleContents()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        ArrayList<Long> ids = scheduleSaveRequestDto.getPicsId();
        List<Pic> pics = new ArrayList<>();
        //requestbody로 받아온 담당 유저들의 id를 돌려서 pic 만들어주기.
        for(Long a : ids){
            User user = userRepository.findById(a)
                    .orElseThrow(()->new NullPointerException("유저없음"));
            //pic객체 만들어 repository에 저장
            Pic pic = new Pic();
            pic.setUser(user);
            pic.setSchedule(savedSchedule);
            picRepository.save(pic);
            pics.add(pic);
        }
        //picRepository에 저장 잘되었는지 확인을 위해 다시 역으로
        List<Long> list = makePicsUserIdList(pics);

        return new ScheduleSaveResponseDto(
                savedSchedule.getId(),
                savedSchedule.getWriterId(),
                savedSchedule.getScheduleTitle(),
                savedSchedule.getScheduleContents(),
                savedSchedule.getWriteDate(),
                savedSchedule.getUpdateDate(),
                list
        );
    }

    @Transactional
    public ScheduleGetResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()->new NullPointerException("일정이 없습니다."));
        List<Long> list = makePicsUserIdList(schedule.getPicList());
        ArrayList<ArrayList<String>> bigList = new ArrayList<>();
        ArrayList<ArrayList<String>> bigList2 = new ArrayList<>();
        for(Long l : list){
            ArrayList<String> smallList = new ArrayList<>();
            User user = userRepository.findById(l).orElseThrow(()->new NullPointerException("유저정보가 없습니다."));
            smallList.add(String.valueOf(user.getId()));
            smallList.add(user.getUserName());
            smallList.add(user.getEmail());
            bigList.add(smallList);
        }
        for(Comment c : schedule.getCommentList()){
            ArrayList<String> smallList2 = new ArrayList<>();
            smallList2.add(c.getCommentWriterName());
            smallList2.add(c.getCommentContents());
            bigList2.add(smallList2);
        }
        return new ScheduleGetResponseDto(
                schedule.getId(),
                schedule.getWriterId(),
                bigList,
                schedule.getScheduleTitle(),
                schedule.getScheduleContents(),
                bigList2,
                schedule.getWriteDate(),
                schedule.getUpdateDate());
    }

    @Transactional
    public List<ScheduleGetAllResponseDto> getSchedules() {
        List<Schedule> scheduleList = scheduleRepository.findAll();
        List<ScheduleGetAllResponseDto> dto = new ArrayList<>();
        for(Schedule s : scheduleList){
            List<Long> list = makePicsUserIdList(s.getPicList());
            dto.add(new ScheduleGetAllResponseDto(s.getId(),
                    s.getWriterId(),
                    list,
                    s.getScheduleTitle(),
                    s.getScheduleContents(),
                    s.getWriteDate(),
                    s.getUpdateDate()));
        }
        return dto;
    }

    @Transactional
    public ScheduleUpdateResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()->new NullPointerException("일정이 없습니다."));
        schedule.update(scheduleUpdateRequestDto.getScheduleTitle(),
                scheduleUpdateRequestDto.getScheduleContents());
        return new ScheduleUpdateResponseDto(
                schedule.getId(),
                schedule.getWriterId(),
                schedule.getScheduleTitle(),
                schedule.getScheduleContents(),
                schedule.getWriteDate(),
                schedule.getUpdateDate()
        );
    }

    //페이지네이션
    public List<SchedulePageResponseDto> schedulePage(Pageable pageable){
        Page<Schedule> page = scheduleRepository.findAll(pageable);
        List<SchedulePageResponseDto> dto = new ArrayList<>();

        for(Schedule s : page){
            User user = userRepository.findById(s.getWriterId()).orElseThrow(()->new NullPointerException("유저없음"));
            SchedulePageResponseDto a = new SchedulePageResponseDto(s.getScheduleTitle(),
                    s.getScheduleContents(),
                    s.countComment(s.getCommentList()),
                    user.getUserName(),
                    s.getWriteDate(),
                    s.getUpdateDate());
            dto.add(a);
        }
        return dto;
    }

    //담당 유저들 id 리스트
    public List<Long> makePicsUserIdList(List<Pic> pics){
        List<Long> userIdList =new ArrayList<>();
        for(Pic p : pics){
            userIdList.add(p.getUser().getId());
        }
        return userIdList;
    }




}
