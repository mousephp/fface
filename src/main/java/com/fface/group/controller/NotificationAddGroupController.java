package com.fface.group.controller;

import com.fface.group.model.entity.Group;
import com.fface.chat.entity.Message;
import com.fface.group.model.entity.NotificationAddGroup;
import com.fface.manager.model.entity.UserInfo;

import com.fface.base.exception.NotFoundException;
import com.fface.group.service.group.GroupService;
import com.fface.chat.service.message.MessageService;
import com.fface.group.service.notifiationAddGroup.NotificationGroupService;
import com.fface.manager.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/notificationAddGroups")
public class NotificationAddGroupController {
    @Autowired
    private NotificationGroupService notificationGroupService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private GroupService groupService;

    @PostMapping("/{fromUserId}/{toUserId}/{group_id}")
    public ResponseEntity<NotificationAddGroup> createNotificationAddGroup(@PathVariable Long fromUserId, @PathVariable Long toUserId, @PathVariable Long group_id) {
        Group group = groupService.findById(group_id).get();

        UserInfo fromUser = userInfoService.findByUserId(fromUserId).get();
        UserInfo toUser = userInfoService.findByUserId(toUserId).get();
        Optional<NotificationAddGroup> optionalNotificationAddGroup = notificationGroupService.findNotification(group_id,fromUser.getId());
        if (!optionalNotificationAddGroup.isPresent()) {
            NotificationAddGroup notificationAddGroup = new NotificationAddGroup();
            notificationAddGroup.setFromUser(fromUser);
            notificationAddGroup.setToUser(toUser);
            notificationAddGroup.setStatus(0);
            notificationAddGroup.setGroup(group);

            Message newMessage = new Message();
            newMessage.setFromUser(fromUser);
            newMessage.setToUser(toUser);
            Date date = new Date();
            newMessage.setDateCreated(date);
            newMessage.setContent("Gửi yêu cầu vào nhóm!");
            newMessage.setStatus(1);
            this.messageService.save(newMessage);

            notificationGroupService.save(notificationAddGroup);
            return new ResponseEntity<>(notificationAddGroup, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/add/{fromUserId}/{toUserId}/{group_id}")
    public ResponseEntity<NotificationAddGroup> addNotificationAddGroup(@PathVariable Long fromUserId, @PathVariable Long toUserId, @PathVariable Long group_id) {
        Group group = groupService.findById(group_id).get();

        UserInfo fromUser = userInfoService.findByUserId(fromUserId).get();
        UserInfo toUser = userInfoService.findByUserId(toUserId).get();
        Optional<NotificationAddGroup> optionalNotificationAddGroup = notificationGroupService.findNotificationByGroupIdAndFromUserId(fromUser.getId(),group_id, toUser.getId());
        if (!optionalNotificationAddGroup.isPresent()) {
            NotificationAddGroup notificationAddGroup = new NotificationAddGroup();
            notificationAddGroup.setFromUser(fromUser);
            notificationAddGroup.setToUser(toUser);
            notificationAddGroup.setStatus(1);
            notificationAddGroup.setGroup(group);

            Message newMessage = new Message();
            newMessage.setFromUser(fromUser);
            newMessage.setToUser(toUser);
            Date date = new Date();
            newMessage.setDateCreated(date);
            newMessage.setContent("Mời bạn vào nhóm !");
            newMessage.setStatus(3);
            this.messageService.save(newMessage);

            notificationGroupService.save(notificationAddGroup);
            return new ResponseEntity<>(notificationAddGroup, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/showNotificationAddGroup/{toUserId}")
    public ResponseEntity<List<NotificationAddGroup>> showAllNotificationAddGroup(@PathVariable Long toUserId) {
        UserInfo userInfo = userInfoService.findByUserId(toUserId).get();
        List<NotificationAddGroup> notificationAddGroupList = this.notificationGroupService.showNotificationAddGroup(userInfo.getId());
        return new ResponseEntity<>(notificationAddGroupList, HttpStatus.OK);
    }

    @GetMapping("/showNotificationAddGroup/add/{toUserId}")
    public ResponseEntity<List<NotificationAddGroup>> showAllNotificationAddGroup1(@PathVariable Long toUserId) {
        UserInfo userInfo = userInfoService.findByUserId(toUserId).get();
        List<NotificationAddGroup> notificationAddGroupList = this.notificationGroupService.showNotificationAddGroup1(userInfo.getId());
        return new ResponseEntity<>(notificationAddGroupList, HttpStatus.OK);
    }

    @GetMapping("/showNotificationAddGroupF/{formUserId}")
    public ResponseEntity<List<NotificationAddGroup>> showAllNotificationAddGroupForm(@PathVariable Long formUserId) {
        UserInfo userInfo = userInfoService.findByUserId(formUserId).get();
        List<NotificationAddGroup> notificationAddGroupList = this.notificationGroupService.showNotificationAddGroupForm(userInfo.getId());
        return new ResponseEntity<>(notificationAddGroupList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NotificationAddGroup> deleteNoti(@PathVariable Long id) {
        this.notificationGroupService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/from/{id}")
    public ResponseEntity<List<NotificationAddGroup>> showAllNotiFormUser(@PathVariable Long id) {
        UserInfo userInfo = this.userInfoService.findByUserId(id).orElseThrow(
                () -> new NotFoundException("NotificationAddGroup", "id", id)
        );

        List<NotificationAddGroup> listNoti = this.notificationGroupService.showNotificationAddGroupForm11(userInfo.getId());

        return new ResponseEntity<>(listNoti, HttpStatus.OK);
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<List<NotificationAddGroup>> showAllNotiFromGroup(@PathVariable Long id) {
        try {
            List<NotificationAddGroup> listNoti = this.notificationGroupService.showNotificationAddGroupForm2(id);
            
            return new ResponseEntity<>(listNoti, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found", e);
        }
    }
}
