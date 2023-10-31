package com.fface.group.controller;

import com.fface.base.exception.NotFoundException;
import com.fface.group.model.entity.NotificationCheckStatus;
import com.fface.manager.model.entity.UserInfo;
import com.fface.group.service.notificationCheckStatus.NotificationCheckStatusService;
import com.fface.manager.service.userInfo.UserInfoService;
import com.fface.base.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/checkStatus")
public class NotificationCheckStatusController {
    @Autowired
    private NotificationCheckStatusService notificationCheckStatusService;
    
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/{adminId}")
    public ResponseEntity<ResponseMessage>  listNotification(@PathVariable Long adminId) {
        UserInfo userInfo = this.userInfoService.findByUserId(adminId).get();

        if (userInfo == null) {
            throw new NotFoundException("Admin not found");
        }

        List<NotificationCheckStatus> listNotificationOfAdmin = this.notificationCheckStatusService.listNoti(userInfo.getId());

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Success", listNotificationOfAdmin), HttpStatus.OK);
    }
}
