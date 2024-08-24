package com.sparta.upgradeschedule.service;

import com.sparta.upgradeschedule.dto.comment.requestDto.commentSaveRequestDto;
import com.sparta.upgradeschedule.dto.comment.responseDto.commentSaveResponseDto;
import com.sparta.upgradeschedule.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public commentSaveResponseDto saveComment(commentSaveRequestDto commentSaveRequestDto) {
        return null;
    }
}
