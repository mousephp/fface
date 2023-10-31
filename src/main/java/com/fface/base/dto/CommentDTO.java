package com.fface.base.dto;

import com.fface.post.model.entity.PostUser;
import com.fface.manager.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;

    private String content;

    private String dateCreated;

    private UserInfo userInfoPost;

    private PostUser postUser;
}
