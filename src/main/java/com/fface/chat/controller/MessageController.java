package com.fface.chat.controller;

import com.fface.base.dto.MessageFrontendDto;
import com.fface.chat.entity.Message;
import com.fface.manager.model.entity.UserInfo;
import com.fface.chat.service.message.MessageService;
import com.fface.post.service.postUser.PostUserService;
import com.fface.manager.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageServicel;
    @Autowired
    private PostUserService postUserService;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/{toUserId}")
    public ResponseEntity<List<MessageFrontendDto>> showAllMessages(@PathVariable Long toUserId){
        UserInfo userInfo = this.userInfoService.findByUserId(toUserId).get();
        List<Message> messages = messageServicel.findAllMessage(userInfo.getId());
        List<MessageFrontendDto> messages1= new ArrayList<>();
        if (messages.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (int i = 0; i < messages.size(); i++) {
            messages1.add(new MessageFrontendDto(messages.get(i).getId(),messages.get(i).getFromUser(),messages.get(i).getToUser()
            , postUserService.getDiffDays(messages.get(i).getDateCreated(),new Date()),messages.get(i).getContent(),messages.size(),messages.get(i).getStatus()));
        }
        return new ResponseEntity<>(messages1,HttpStatus.OK);
    }
    @GetMapping("/total/{toUserId}")
    public ResponseEntity<Integer> totalMessage(@PathVariable Long toUserId){
        UserInfo toUserInfo  = this.userInfoService.findByUserId(toUserId).get();
        Integer totalMessage  = this.messageServicel.findAllMessage(toUserInfo.getId()).size();
        return new ResponseEntity<>(totalMessage, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteMessage(@PathVariable Long id) {
         this.messageServicel.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
