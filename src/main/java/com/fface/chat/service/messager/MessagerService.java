package com.fface.chat.service.messager;

import com.fface.chat.entity.Messager;
import com.fface.base.service.GeneralService;

import java.util.List;

public interface MessagerService extends GeneralService<Messager> {
    List<Messager> findAllMessagers(Long from_user_id, Long to_user_id);
    List<Messager> allMessOfUser(Long userId);
}
