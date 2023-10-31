package com.fface.base.dto;

import com.fface.manager.model.entity.UserInfo;
import com.fface.post.model.entity.CommentPostUser;
import com.fface.post.model.entity.ImagePostUser;
import com.fface.post.model.entity.ReplyComment;
import com.fface.post.model.entity.StatusPost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostUserFrontEndDto {
    private Long postUserId;

    private String content;

    private ImagePostUser[] listImage;

    private String dateCreated;

    private UserInfo userInfo;

    private Integer totalLike;

    private List<CommentPostUser> comments;

    private int totalComments;

    private StatusPost status;

    private List<TotalLikeCm> totalLikeCm;

    private List<ReplyComment> listReply;

    private int totalReply;

    public PostUserFrontEndDto(Long postUserId, String content, ImagePostUser[] listImage, String dateCreated, UserInfo userInfo, StatusPost status) {
        this.postUserId = postUserId;
        this.content = content;
        this.listImage = listImage;
        this.dateCreated = dateCreated;
        this.userInfo = userInfo;
        this.status = status;
    }
}
