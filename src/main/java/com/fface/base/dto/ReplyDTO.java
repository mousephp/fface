package com.fface.base.dto;

import com.fface.post.model.entity.CommentPostUser;
import com.fface.post.model.entity.PostUser;
import com.fface.manager.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
    private Long id;

    private String content;

    private String dateCreated;

    private int totalReply;

    private UserInfo userInfoComment;

    private CommentPostUser comment;

    private PostUser postUser;
}
