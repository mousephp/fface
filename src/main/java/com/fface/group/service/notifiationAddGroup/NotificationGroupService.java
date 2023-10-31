package com.fface.group.service.notifiationAddGroup;

import com.fface.group.model.entity.NotificationAddGroup;
import com.fface.base.service.GeneralService;

import java.util.List;
import java.util.Optional;

public interface NotificationGroupService extends GeneralService<NotificationAddGroup> {
    List<NotificationAddGroup> showNotificationAddGroup(Long toUserId);
    List<NotificationAddGroup> showNotificationAddGroupForm(Long formUserId);

    Optional<NotificationAddGroup> findNotification(Long groupId, Long fromId);
    Optional<NotificationAddGroup> findNotificationByGroupIdAndFromUserId(Long fromUserId, Long groupId, Long toUserId);
    List<NotificationAddGroup> showNotificationAddGroup1(Long toUserId);
    List<NotificationAddGroup> showNotificationAddGroupForm11(Long formUserId);
    List<NotificationAddGroup> showNotificationAddGroupForm2(Long groupId);

    Object getNotificationById(Long id);
}
