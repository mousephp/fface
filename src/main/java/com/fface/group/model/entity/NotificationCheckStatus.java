package com.fface.group.model.entity;

import com.fface.manager.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationCheckStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserInfo admin;

    @OneToOne
    private PostGroup postGroup;
}
