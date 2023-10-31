package com.fface.group.service.notifiationAddGroup;

import com.fface.group.model.entity.NotificationAddGroup;
import com.fface.base.exception.NotFoundException;
import com.fface.group.respository.NotificationAddGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationGroupServiceImpl implements NotificationGroupService {
    @Autowired
    private NotificationAddGroupRepository notificationAddGroupRepository;

    @Override
    public Page<NotificationAddGroup> findAll(Pageable pageable) {
        return notificationAddGroupRepository.findAll(pageable);
    }

    @Override
    public Optional<NotificationAddGroup> findById(Long id) {
        return notificationAddGroupRepository.findById(id);
    }

    @Override
    public NotificationAddGroup save(NotificationAddGroup notificationAddGroup) {
        return notificationAddGroupRepository.save(notificationAddGroup);
    }

    @Override
    public void deleteById(Long id) {
        NotificationAddGroup notificationAddGroup = notificationAddGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NotificationAddGroup", "id", id));

        notificationAddGroupRepository.delete(notificationAddGroup);
    }

    public NotificationAddGroup getNotificationById(Long id){
        return notificationAddGroupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NotificationAddGroup", "id", id));
    }

    @Override
    public List<NotificationAddGroup> showNotificationAddGroup(Long toUserId) {
        return notificationAddGroupRepository.showNotificationAddGroup(toUserId);
    }

    @Override
    public List<NotificationAddGroup> showNotificationAddGroupForm(Long formUserId) {
        return this.notificationAddGroupRepository.showNotificationAddGroupForm(formUserId);
    }

    @Override
    public Optional<NotificationAddGroup> findNotification(Long groupId, Long fromId) {
        return notificationAddGroupRepository.findNotification(groupId, fromId);
    }

    @Override
    public Optional<NotificationAddGroup> findNotificationByGroupIdAndFromUserId(Long fromUserId, Long groupId, Long toUserId) {
        return notificationAddGroupRepository.findNotificationByGroupIdAndFromUserId(fromUserId, groupId, toUserId);
    }

    @Override
    public List<NotificationAddGroup> showNotificationAddGroup1(Long toUserId) {
        return notificationAddGroupRepository.showNotificationAddGroup1(toUserId);
    }

    @Override
    public List<NotificationAddGroup> showNotificationAddGroupForm11(Long formUserId) {
        return this.notificationAddGroupRepository.showNotificationAddGroupForm11(formUserId);
    }

    @Override
    public List<NotificationAddGroup> showNotificationAddGroupForm2(Long groupId) {
        return this.notificationAddGroupRepository.showNotificationAddGroupForm2(groupId);
    }
}
