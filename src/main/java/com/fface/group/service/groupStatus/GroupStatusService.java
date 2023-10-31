package com.fface.group.service.groupStatus;

import com.fface.group.model.entity.GroupStatus;
import com.fface.base.service.GeneralService;

import java.util.List;

public interface GroupStatusService extends GeneralService<GroupStatus> {
    List<GroupStatus> findAll();
}
