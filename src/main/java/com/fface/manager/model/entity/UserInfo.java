package com.fface.manager.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fface.post.model.entity.PostUser;
import lombok.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_info")
@Builder
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String fullName;

    private String phoneNumber;

    private String dateOfBirth;

    private String address;

    private String avatar;

    private String backGround;

    private String interest;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<PostUser> share = new HashSet<>();

    public UserInfo(String email, String fullName, String phoneNumber, String dateOfBirth, String address, String avatar, String backGround, String interest, User user) {
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.avatar = avatar;
        this.backGround = backGround;
        this.interest = interest;
        this.user = user;
    }

    public UserInfo(Long id, String email, String fullName, String phoneNumber, String dateOfBirth, String address, String avatar, String backGround, User user) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.avatar = avatar;
        this.backGround = backGround;
        this.user = user;
    }

    public UserInfo(String email, String fullName, String phoneNumber, String dateOfBirth, String address, String avatar, String backGround, User user) {
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.avatar = avatar;
        this.backGround = backGround;
        this.user = user;
    }

    public UserInfo(String email, String phoneNumber, String dateOfBirth, String avatar, String backGround, User user) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.avatar = avatar;
        this.backGround = backGround;
        this.user = user;
    }
}
