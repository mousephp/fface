package com.fface.chat.entity;

import com.fface.manager.model.entity.User;
import lombok.Data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Data
public class Chat1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private String content;

    private boolean status;

    private Date time;
}
