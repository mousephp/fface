package com.fface.post.model.entity;

import com.fface.manager.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Date dateCreated;

    @ManyToOne
    private UserInfo userInfoComment;

    @ManyToOne
    private CommentPostUser comment;

    @ManyToOne
    private PostUser postUser;
}
