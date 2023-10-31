package com.fface.chat.service.chat1;

import com.fface.chat.entity.Chat1;
import com.fface.base.service.GeneralService;

import java.util.List;

public interface Chat1Service extends GeneralService<Chat1> {
    Iterable<Chat1> getAllHistoryBetweenTwoUser(Long user1Id, Long user2Id);

    List<Chat1> listChatOfMe(Long senderId, Long receiverId);

}
