package com.fface.chat.service.messager;

import com.fface.chat.entity.Messager;
import com.fface.chat.repository.MessagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessagerServiceImpl implements MessagerService {
    @Autowired
    private MessagerRepository messageRepository;
    
    @Override
    public Page<Messager> findAll(Pageable pageable) {
        return this.messageRepository.findAll(pageable);
    }

    @Override
    public Optional<Messager> findById(Long id) {
        return this.messageRepository.findById(id);
    }

    @Override
    public Messager save(Messager messager) {
        return this.messageRepository.save(messager);
    }

    @Override
    public void deleteById(Long id) {
        this.messageRepository.deleteById(id);
    }

    @Override
    public List<Messager> findAllMessagers(Long from_user_id, Long to_user_id) {
        return this.messageRepository.findAllMessagers(from_user_id, to_user_id);
    }

    @Override
    public List<Messager> allMessOfUser(Long userId) {
        return this.messageRepository.allMessOfUser(userId);
    }
}
