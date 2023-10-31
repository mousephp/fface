package com.fface.post.model.dto;

import com.fface.manager.model.entity.UserInfo;
import com.fface.post.model.entity.PostUser;
import lombok.Data;

@Data
public class CommentPostUserDto {
    private Long id;

    private String content;

    private String dateCreated;

    private PostUser postUser;

    private UserProfileDTO userInfoPost;
}
