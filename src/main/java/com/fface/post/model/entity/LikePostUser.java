package com.fface.post.model.entity;

import com.fface.manager.model.entity.UserInfo;
import lombok.*;
import jakarta.persistence.*;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "like_post_user")
public class LikePostUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_user_id")
    private PostUser postUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    public LikePostUser(boolean status, PostUser postUser, UserInfo userInfo) {
        this.status = status;
        this.postUser = postUser;
        this.userInfo = userInfo;
    }
}
