package com.fface.chat.controller;

import com.fface.base.dto.MessagerForm;
import com.fface.chat.entity.Chat;
import com.fface.chat.entity.Messager;
import com.fface.manager.model.entity.UserInfo;
import com.fface.chat.service.chat.ChatService;
import com.fface.chat.service.message.MessageService;
import com.fface.chat.service.messager.MessagerServiceImpl;
import com.fface.post.service.postUser.PostUserService;
import com.fface.manager.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/messager")
public class MessagerController {
    @Autowired
    private MessagerServiceImpl messageService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PostUserService postUserService;

    @Autowired
    private MessageService messageService1;

    @Autowired
    private ChatService chatService;
    
    @PostMapping("/{formId}/{toId}")
        public ResponseEntity<Messager> createMessage(@PathVariable Long formId, @PathVariable Long toId, @RequestBody MessagerForm messagerForm) {
        UserInfo formUser = this.userInfoService.findByUserId(formId).get();
        UserInfo toUser = this.userInfoService.findByUserId(toId).get();
        Optional<Chat> chatOptional = this.chatService.findChat(formUser.getId(), toUser.getId());
        Chat chat = new Chat();
        Messager messager = new Messager();
        if (!chatOptional.isPresent()) {
            chat.setUser1(formUser);
            chat.setUser2(toUser);
            chat.setLastContent(messagerForm.getContent());
            chat.setDateCreated(new Date());
            chatService.save(chat);
            messager.setChat(chat);

        } else {
            Chat oldChat = chatOptional.get();
            oldChat.setId(oldChat.getId());
            oldChat.setLastContent(messagerForm.getContent());
            oldChat.setDateCreated(new Date());
            chatService.save(oldChat);
        }
        messager.setFromUser(formUser);
        messager.setToUser(toUser);
        messager.setContent(messagerForm.getContent());
        messager.setDateCreated(new Date());
        this.messageService.save(messager);
        return new ResponseEntity<>(messager, HttpStatus.OK);
        }
    }

