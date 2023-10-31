package com.fface.chat.controller;

import com.fface.base.dto.MessagerDTO;
import com.fface.chat.entity.Chat1;
import com.fface.chat.service.chat.ChatService;
import com.fface.chat.service.chat1.Chat1Service;
import com.fface.manager.model.entity.UserInfo;
import com.fface.post.service.postUser.PostUserService;
import com.fface.manager.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/chats")
public class Chat1Controller {
    @Autowired
    private Chat1Service chatService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ChatService groupChatService;

    @Autowired
    private PostUserService postUserService;

    @GetMapping
    public ResponseEntity<List<MessagerDTO>> getAllChat(@RequestParam("userId1") Long userId1,
                                                        @RequestParam("userId2") Long userId2
                                                     ) {
        List<Chat1> listChat = (List<Chat1>) chatService.getAllHistoryBetweenTwoUser(userId1, userId2);
        List<MessagerDTO> chats = new ArrayList<>();
        for (int i = 0; i < listChat.size(); i++) {
            chats.add(new MessagerDTO(listChat.get(i).getId(),
                                        listChat.get(i).getSender(),
                                        listChat.get(i).getReceiver(),
                                        listChat.get(i).getContent(),
                                        listChat.get(i).isStatus(),
                                           postUserService.getDiffDays(listChat.get(i).getTime(), new Date()) ));
        }
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Chat1> createNewChat(@RequestBody Chat1 chat) {
        long milis = System.currentTimeMillis();
        Date date = new Date();
        chat.setTime(date);
        chatService.save(chat);

        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat1> getChat(@PathVariable Long id) {
        Optional<Chat1> categoryOptional = chatService.findById(id);
        return categoryOptional.map(chat -> new ResponseEntity<>(chat, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chat1> updateChat(@PathVariable Long id, @RequestBody Chat1 chat) {
        Optional<Chat1> categoryOptional = chatService.findById(id);
        return categoryOptional.map(chat1 -> {
            chat.setId(chat1.getId());
            return new ResponseEntity<>(chatService.save(chat), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id1}/{id2}")
    public ResponseEntity<Chat1> deleteChat(@PathVariable Long id1, @PathVariable Long id2) {
        UserInfo user1 = this.userInfoService.findByUserId(id1).get();
        UserInfo user2 = this.userInfoService.findByUserId(id2).get();
      List<Chat1> listChat = this.chatService.listChatOfMe(user1.getId(), user2.getId());
        for (int i = 0; i < listChat.size(); i++) {
            this.chatService.deleteById(listChat.get(i).getId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
