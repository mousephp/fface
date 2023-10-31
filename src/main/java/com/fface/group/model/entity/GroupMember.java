package com.fface.group.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fface.manager.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "group_members")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    @JsonBackReference
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_info_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private UserInfo userInfo;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private int status;

    public GroupMember(Group group, UserInfo userInfo, int status) {
        this.group = group;
        this.userInfo = userInfo;
        this.status = status;
    }
}
