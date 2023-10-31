package com.fface.group.respository;

import com.fface.group.model.entity.GroupMember;
import com.fface.group.model.entity.NotificationAddGroup;
import com.fface.manager.model.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    @Query(value = "select * from notification_add_groups where from_user_id = ?1 and  group_id = ?2", nativeQuery = true)
    Optional<NotificationAddGroup> findNotificationAccept(Long fromUserId, Long groupId);

    @Query(value = "select * from group_members where group_id = ?1  and status != 0", nativeQuery = true)
    List<GroupMember> findGroupMemberByGroupId(Long groupId);
    
    @Query(value = "select * from group_members where user_info_id = ?1 and status = 1", nativeQuery = true)
    List<GroupMember> findGroupByUserId(Long groupId);

    @Query(value = "select * from group_members where status = 1 and user_info_id = ? ",nativeQuery = true)
    List<GroupMember> findGroupsOfMe(Long userId);

    @Query(value = "select * from group_members where status = 1 and group_id = ?1 and user_info_id =?2", nativeQuery = true)
    Optional<GroupMember> findMemberOut(Long groupId, Long userId);

    GroupMember findByUserInfo(Optional<UserInfo> userInfo);

    @Query(value = "select * from group_members where user_info_id = ?1 and group_id = ?2 LIMIT 1", nativeQuery = true)
    Optional<GroupMember> findByUserAndGroup(Long UserId, Long groupId);
}
