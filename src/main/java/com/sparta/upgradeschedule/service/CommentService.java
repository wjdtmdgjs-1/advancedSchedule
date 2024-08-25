package com.sparta.upgradeschedule.service;

import com.sparta.upgradeschedule.dto.comment.requestDto.CommentSaveRequestDto;
import com.sparta.upgradeschedule.dto.comment.requestDto.CommentUpdateRequestDto;
import com.sparta.upgradeschedule.dto.comment.responseDto.CommentGetResponseDto;
import com.sparta.upgradeschedule.dto.comment.responseDto.CommentSaveResponseDto;
import com.sparta.upgradeschedule.dto.comment.responseDto.CommentUpdateResponseDto;
import com.sparta.upgradeschedule.entity.Comment;
import com.sparta.upgradeschedule.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public CommentSaveResponseDto saveComment(CommentSaveRequestDto commentSaveRequestDto) {
        Comment comment = new Comment(commentSaveRequestDto.getCommentWriterName(),
                commentSaveRequestDto.getCommentContents());
        Comment savedComment = commentRepository.save(comment);
        return new CommentSaveResponseDto(savedComment.getId(),
                savedComment.getCommentWriterName(),
                savedComment.getCommentContents(),
                savedComment.getCommentWriteDate(),
                savedComment.getCommentUpdateDate());
    }

    public CommentGetResponseDto getComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new NullPointerException("댓글 없습니다."));
        return new CommentGetResponseDto(comment.getId(),
                comment.getCommentWriterName(),
                comment.getCommentContents(),
                comment.getCommentWriteDate(),
                comment.getCommentUpdateDate());
    }

    public List<CommentGetResponseDto> getComments() {
        List<Comment> commentList = commentRepository.findAll();
        List<CommentGetResponseDto> dto =new ArrayList<>();
        for(Comment c:commentList){
            dto.add(new CommentGetResponseDto(c.getId(),
                    c.getCommentWriterName(),
                    c.getCommentContents(),
                    c.getCommentWriteDate(),
                    c.getCommentUpdateDate()));
        }
        return dto;
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(Long id, CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new NullPointerException("댓글 없습니다."));
        comment.update(commentUpdateRequestDto.getCommentContents());
        return new CommentUpdateResponseDto(comment.getId(),
                comment.getCommentWriterName(),
                comment.getCommentContents(),
                comment.getCommentWriteDate(),
                comment.getCommentUpdateDate());
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()->new NullPointerException("댓글 없습니다."));
        commentRepository.delete(comment);
    }
}
