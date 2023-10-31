package com.fface.post.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fface.manager.model.entity.UserInfo;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql =
        "UPDATE comment_post_user " +
                "SET is_delete = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findCommentPostUserById")
@NamedQuery(name = "findCommentPostUserById", query =
        "SELECT t " +
                "FROM CommentPostUser t " +
                "WHERE " +
                "    t.id = ?1 AND " +
                "    t.is_delete = false ")
@Where(clause = "is_delete = false")
public class CommentPostUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Date dateCreated;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private UserInfo userInfoPost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_user_id", nullable = false)
    @JsonBackReference
    private PostUser postUser;

    @Column(name = "isDelete")
    private boolean is_delete;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public CommentPostUser(String content, Date dateCreated, UserInfo userInfoPost, PostUser postUser) {
        this.content = content;
        this.dateCreated = dateCreated;
        this.userInfoPost = userInfoPost;
        this.postUser = postUser;
    }
}
