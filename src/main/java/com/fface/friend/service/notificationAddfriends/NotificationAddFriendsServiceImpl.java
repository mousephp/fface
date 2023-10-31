package com.fface.friend.service.notificationAddfriends;

import com.fface.friend.entity.NotificationAddFriends;
import com.fface.friend.repository.NotificationAddFriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationAddFriendsServiceImpl implements NotificationAddFriendsService {
    @Autowired
    private NotificationAddFriendRepository notifiCationAddFriendRepository;

    @Override
    public Page<NotificationAddFriends> findAll(Pageable pageable) {
        return notifiCationAddFriendRepository.findAll(pageable);
    }

    @Override
    public Optional<NotificationAddFriends> findById(Long id) {
        return notifiCationAddFriendRepository.findById(id);
    }

    @Override
    public NotificationAddFriends save(NotificationAddFriends notificationAddFriends) {
        return notifiCationAddFriendRepository.save(notificationAddFriends);
    }

    @Override
    public void deleteById(Long id) {
        notifiCationAddFriendRepository.deleteById(id);
    }

    @Override
    public List<NotificationAddFriends> showNotifications(Long toUserId) {
        return notifiCationAddFriendRepository.showNotifications(toUserId);
    }

    @Override
    public Optional<NotificationAddFriends> findNotificationAddFriends(Long fromUserId, Long toUserId) {
        return this.notifiCationAddFriendRepository.findNotificationAddFriends(fromUserId, toUserId);
    }

    @Override
    public List<NotificationAddFriends> showAllSendNotifications(Long fromUserId) {
        return this.notifiCationAddFriendRepository.showAllSendNotifications(fromUserId);
    }
}
