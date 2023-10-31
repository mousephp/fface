package com.fface.group.service.notificationCheckStatus;

import com.fface.group.model.entity.NotificationCheckStatus;
import com.fface.base.service.GeneralService;

import java.util.List;

public interface NotificationCheckStatusService extends GeneralService<NotificationCheckStatus> {
    List<NotificationCheckStatus> listNoti(Long adminId);
}
