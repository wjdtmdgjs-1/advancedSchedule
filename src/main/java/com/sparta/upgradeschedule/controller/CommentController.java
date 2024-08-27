package com.sparta.upgradeschedule.controller;

import com.sparta.upgradeschedule.dto.comment.requestDto.CommentSaveRequestDto;
import com.sparta.upgradeschedule.dto.comment.requestDto.CommentUpdateRequestDto;
import com.sparta.upgradeschedule.dto.comment.responseDto.CommentGetResponseDto;
import com.sparta.upgradeschedule.dto.comment.responseDto.CommentSaveResponseDto;
import com.sparta.upgradeschedule.dto.comment.responseDto.CommentUpdateResponseDto;
import com.sparta.upgradeschedule.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommentSaveResponseDto> saveComment(@RequestBody CommentSaveRequestDto commentSaveRequestDto) {
        return ResponseEntity.ok(commentService.saveComment(commentSaveRequestDto));
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentGetResponseDto> getComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @GetMapping("/comments")
    public ResponseEntity<List<CommentGetResponseDto>> getComments() {
        return ResponseEntity.ok(commentService.getComments());
    }

    //수정은 댓글 내용만 수정할 수 있다.
    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentUpdateResponseDto> updateComment(@PathVariable Long id,
                                                                  @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        return ResponseEntity.ok(commentService.updateComment(id, commentUpdateRequestDto));
    }

    @DeleteMapping("/comments/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
