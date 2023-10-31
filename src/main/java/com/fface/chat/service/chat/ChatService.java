package com.fface.chat.service.chat;

import com.fface.chat.entity.Chat;
import com.fface.base.service.GeneralService;

import java.util.List;
import java.util.Optional;

public interface ChatService extends GeneralService<Chat> {
    Optional<Chat> findChat(Long user1Id, Long user2Id);
    List<Chat> getAllChatByUser(Long userId);


}
