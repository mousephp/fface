package com.fface.chat.service.chat1;

import com.fface.chat.entity.Chat1;
import com.fface.chat.repository.Chat1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Chat1ServiceImpl implements Chat1Service{
    @Autowired
    private Chat1Repository chatRepository;

    @Override
    public Page<Chat1> findAll(Pageable pageable) {
        return chatRepository.findAll(pageable);
    }

    @Override
    public Optional<Chat1> findById(Long id) {
        return chatRepository.findById(id);
    }

    @Override
    public Chat1 save(Chat1 chat1) {
        return chatRepository.save(chat1);
    }

    @Override
    public void deleteById(Long id) {
            chatRepository.deleteById(id);
    }

    @Override
    public Iterable<Chat1> getAllHistoryBetweenTwoUser(Long user1Id, Long user2Id) {
        return chatRepository.getAllHistoryBetweenTwoUser(user1Id, user2Id);
    }

    @Override
    public List<Chat1> listChatOfMe(Long senderId, Long receiverId) {
        return this.chatRepository.listChatOfMe(senderId, receiverId);
    }
}
