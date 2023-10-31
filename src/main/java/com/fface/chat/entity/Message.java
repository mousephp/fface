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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserInfo fromUser;

    @ManyToOne
    private UserInfo toUser;

    private Date dateCreated;

    private String content;
    private int status;

    public Message(UserInfo fromUser, UserInfo toUser, Date dateCreated) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.dateCreated = dateCreated;
    }
}
