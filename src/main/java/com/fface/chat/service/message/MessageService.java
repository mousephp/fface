package com.fface.chat.service.message;

import com.fface.chat.entity.Message;
import com.fface.base.service.GeneralService;

import java.util.List;

public interface MessageService extends GeneralService<Message> {

    List<Message> findAllMessage(Long to_user_id);
    void deleteMessage(Long toUserId);

}
