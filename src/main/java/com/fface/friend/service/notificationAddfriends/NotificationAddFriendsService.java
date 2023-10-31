package com.fface.friend.service.notificationAddfriends;

import com.fface.friend.entity.NotificationAddFriends;
import com.fface.base.service.GeneralService;

import java.util.List;
import java.util.Optional;

public interface NotificationAddFriendsService extends GeneralService<NotificationAddFriends> {
    List<NotificationAddFriends> showNotifications(Long toUserId);

    Optional<NotificationAddFriends> findNotificationAddFriends(Long fromUserId, Long toUserId);
    List<NotificationAddFriends>  showAllSendNotifications(Long fromUserId);

}
