package com.fface.post.service.commentPostUser;

import com.fface.post.model.dto.CommentPostUserDto;
import com.fface.post.model.entity.CommentPostUser;

import java.util.List;
import java.util.Optional;


public interface CommentPostUserService {
    List<CommentPostUser> displayAllCommentOfPost(Long postUserId);

    Optional<CommentPostUser> findById(Long id);

    /*===================*/
    CommentPostUserDto storeComment(long UserId, long postUserId, CommentPostUserDto commentDto);

    List<CommentPostUserDto> getCommentsByPostId(long postUserId);

    CommentPostUserDto getCommentById(Long postUserId, Long commentId);

    CommentPostUserDto updateComment(Long postUserId, long commentId, CommentPostUserDto commentRequest);

    void destroyComment(Long postUserId, Long commentId);
}