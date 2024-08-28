package com.sparta.upgradeschedule.service;

import com.sparta.upgradeschedule.dto.comment.requestDto.CommentSaveRequestDto;
import com.sparta.upgradeschedule.dto.comment.requestDto.CommentUpdateRequestDto;
import com.sparta.upgradeschedule.dto.comment.responseDto.CommentGetResponseDto;
import com.sparta.upgradeschedule.dto.comment.responseDto.CommentSaveResponseDto;
import com.sparta.upgradeschedule.dto.comment.responseDto.CommentUpdateResponseDto;
import com.sparta.upgradeschedule.entity.Comment;
import com.sparta.upgradeschedule.entity.Schedule;
import com.sparta.upgradeschedule.repository.CommentRepository;
import com.sparta.upgradeschedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;


    public CommentSaveResponseDto saveComment(CommentSaveRequestDto commentSaveRequestDto) {
        Comment comment = new Comment(commentSaveRequestDto.getCommentWriterName(),
                commentSaveRequestDto.getCommentContents());
        Schedule schedule = scheduleRepository.findById(commentSaveRequestDto.getScheduleId())
                .orElseThrow(() -> new NullPointerException("일정이 없습니다."));
        comment.setSchedule(schedule);

        Comment savedComment = commentRepository.save(comment);

        return new CommentSaveResponseDto(savedComment.getId(),
                savedComment.getCommentWriterName(),
                savedComment.getCommentContents(),
                savedComment.getCommentWriteDate(),
                savedComment.getCommentUpdateDate());
    }

    public CommentGetResponseDto getComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("댓글 없습니다."));
        return new CommentGetResponseDto(comment.getId(),
                comment.getCommentWriterName(),
                comment.getCommentContents(),
                comment.getCommentWriteDate(),
                comment.getCommentUpdateDate());
    }

    public List<CommentGetResponseDto> getComments() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentGetResponseDto> dto = new ArrayList<>();
        for (Comment c : commentList) {
            dto.add(new CommentGetResponseDto(c.getId(),
                    c.getCommentWriterName(),
                    c.getCommentContents(),
                    c.getCommentWriteDate(),
                    c.getCommentUpdateDate()));
        }
        return dto;
    }


    public CommentUpdateResponseDto updateComment(Long id, CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("댓글 없습니다."));
        comment.update(commentUpdateRequestDto.getCommentContents());
        return new CommentUpdateResponseDto(comment.getId(),
                comment.getCommentWriterName(),
                comment.getCommentContents(),
                comment.getCommentWriteDate(),
                comment.getCommentUpdateDate());
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("댓글 없습니다."));
        commentRepository.delete(comment);
    }
}
