package com.fface.group.model.entity;

import com.fface.manager.model.entity.UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "member_requests")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MemberRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    @Column(name = "date")
    private Date date;
}
