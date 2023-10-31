package com.fface.post.model.entity;

import com.fface.manager.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserInfo fromUser;

    @ManyToOne
    private CommentPostUser commentPostUser;

    private int status;

    public LikeComment(UserInfo fromUser, CommentPostUser commentPostUser, int status) {
        this.fromUser = fromUser;
        this.commentPostUser = commentPostUser;
        this.status = status;
    }
}
