package com.blog1.sevice;

import com.blog1.paylod.CommentDto;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(long postId , CommentDto commentDto);

    void deleteComment(long commentId);

    List<CommentDto> getCommentByPostId(long postId);

    List<CommentDto> getAllComments();



}
