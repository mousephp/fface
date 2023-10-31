package com.fface.friend.entity;

import com.fface.manager.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "list_friend")
public class ListFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private UserInfo userInfo;

    @ManyToOne
    private UserInfo friendsOfUserinfo;

    public ListFriend(UserInfo userInfo, UserInfo friendsOfUserinfo) {
        this.userInfo = userInfo;
        this.friendsOfUserinfo = friendsOfUserinfo;
    }
}
