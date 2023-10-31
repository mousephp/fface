package com.fface.group.service.groupMember;

import com.fface.group.model.entity.Group;
import com.fface.group.model.entity.GroupMember;
import com.fface.group.model.entity.NotificationAddGroup;
import com.fface.base.service.GeneralService;
import com.fface.group.model.request.GroupMemberRequest;

import java.util.List;
import java.util.Optional;

public interface GroupMemberService extends GeneralService<GroupMember> {
    Optional<NotificationAddGroup> findNotificationAccept(Long fromUserId, Long groupId);

    List<GroupMember> findGroupMemberByGroupId(Long groupId);

    List<GroupMember> findGroupsOfMe(Long userId);

    List<GroupMember> findGroupByUserId(Long groupId);

    Optional<GroupMember> findMemberOut(Long groupId, Long userId);

}
