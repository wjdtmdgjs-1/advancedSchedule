package com.sparta.upgradeschedule.controller;

import com.sparta.upgradeschedule.dto.comment.requestDto.commentSaveRequestDto;
import com.sparta.upgradeschedule.dto.comment.responseDto.commentSaveResponseDto;
import com.sparta.upgradeschedule.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<commentSaveResponseDto> saveComment(@RequestBody commentSaveRequestDto commentSaveRequestDto){
        return ResponseEntity.ok(commentService.saveComment(commentSaveRequestDto));
    }


}
