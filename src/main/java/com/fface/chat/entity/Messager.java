package com.fface.chat.entity;

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
public class Messager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserInfo fromUser;

    @ManyToOne
    private UserInfo toUser;

    private String content;

    private Date dateCreated;
    @ManyToOne
    private Chat chat;

    public Messager(UserInfo fromUser, UserInfo toUser, String content, Date dateCreated) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.content = content;
        this.dateCreated = dateCreated;
    }
}
