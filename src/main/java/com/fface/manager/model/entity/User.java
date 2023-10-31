package com.fface.manager.model.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@Table(name = "user") 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private Boolean blockStatus;

    private Date createDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    private List<Role> roles;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;


    public User(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(String username, String password, Boolean blockStatus, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.blockStatus = blockStatus;
        this.roles = roles;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
