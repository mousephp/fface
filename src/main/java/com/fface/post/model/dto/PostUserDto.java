package com.fface.post.model.dto;

import com.fface.manager.model.entity.UserInfo;
import com.fface.post.model.entity.StatusPost;
import lombok.Data;

import java.util.Date;

@Data
public class PostUserDto {
    private Long id;

    private String content;

    private Date dateCreated;

    private StatusPost status;

    private UserInfo userInfo;
}
