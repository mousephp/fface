package com.fface.group.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fface.base.audit.Auditable;
import com.fface.manager.model.entity.UserInfo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.annotations.SQLDelete;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table( name = "groups_", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql =
        "UPDATE groups_ " +
                "SET is_delete = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findGroup_ById")
@NamedQuery(name = "findGroup_ById", query =
        "SELECT t " +
                "FROM Group t " +
                "WHERE " +
                "    t.id = ?1 AND " +
                "    t.is_delete = false ")
@Where(clause = "is_delete = false")
/*@DynamicUpdate
@DynamicInsert*/
public class Group extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "background")
    private String background;

    @Column(name = "isDelete")
    private boolean is_delete;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserInfo user;

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupStatus groupStatus;

    @JoinTable(
            name = "group_members",
            joinColumns = @JoinColumn(
                    name = "id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "group_id",
                    referencedColumnName = "id"
            )
    )
    @OneToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<GroupMember> groupMember = new ArrayList<>();

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MemberRequest> memberRequest = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "groups_managers",
            joinColumns = { @JoinColumn(name = "group_id") },
            inverseJoinColumns = { @JoinColumn(name = "managers_id") })
    @JsonIgnore
    private Set<Manager> managers = new HashSet<>();

    public Group(String name, GroupStatus groupStatus) {
        this.name = name;
        this.groupStatus = groupStatus;
    }

    public Group(String name, String avatar, String background, UserInfo user, GroupStatus groupStatus) {
        this.name = name;
        this.avatar = avatar;
        this.background = background;
        this.user = user;
        this.groupStatus = groupStatus;
    }
}
