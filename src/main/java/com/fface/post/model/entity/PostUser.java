package com.fface.post.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fface.manager.model.entity.UserInfo;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "post_user")
@SQLDelete(sql =
        "UPDATE post_user " +
                "SET is_delete = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findPostUserById")
@NamedQuery(name = "findPostUserById", query =
        "SELECT t " +
                "FROM PostUser t " +
                "WHERE " +
                "    t.id = ?1 AND " +
                "    t.is_delete = false ")
@Where(clause = "is_delete = false")
public class PostUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Date dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private StatusPost status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo userInfo;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "like_post_user",
            joinColumns = { @JoinColumn(name = "post_user_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_info_id") })
    @JsonIgnore
    private List<UserInfo> likePostUser = new ArrayList<>();

    @OneToMany(mappedBy = "postUser", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<CommentPostUser> commentPostUser = new HashSet<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setId(Long id) {
        this.id = id;
    }

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "isDelete")
    private boolean is_delete;

    public PostUser(String content, Date dateCreated, StatusPost status, UserInfo userInfo) {
        this.content = content;
        this.dateCreated = dateCreated;
        this.status = status;
        this.userInfo = userInfo;
    }

    public Long getId() {
        return id;
    }
}
