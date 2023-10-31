package com.fface.manager.controller;

import com.fface.base.dto.UserDTO;
import com.fface.manager.model.entity.User;
import com.fface.manager.model.entity.UserInfo;
import com.fface.friend.service.listFriend.ListFriendService;
import com.fface.manager.service.user.UserService;
import com.fface.manager.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ListFriendService listFriendService;

    @GetMapping("/all/{id}")
    public ResponseEntity<List<UserDTO>> getAllUser(@PathVariable Long id) {
        List<UserInfo> userInfos = userInfoService.showAllUserInfo(id);
        List<UserDTO> userDTOList = new ArrayList<>();
        if (userInfos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        for (int i = 0; i < userInfos.size(); i++) {
            userDTOList.add(new UserDTO(userInfos.get(i).getUser().getId(),
                    userInfos.get(i).getUser().getUsername(),
                    userInfos.get(i).getUser().getCreateDate(),
                    this.listFriendService.findAll(userInfos.get(i).getId()).size(),
                    userInfos.get(i).getUser().getBlockStatus()));
        }
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable Long userId) {
        Optional<User> optionalUser = this.userService.findById(userId);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> blockUser(@PathVariable long userId) {
        Optional<User> optionalUser = this.userService.findById(userId);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        optionalUser.get().setBlockStatus(true);
        this.userService.saveBlock(optionalUser.get());
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);

    }
    @PutMapping("/unlock/{userId}")
    public ResponseEntity<User> unlockUser(@PathVariable Long userId) {
        Optional<User> optionalUser = this.userService.findById(userId);
        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        optionalUser.get().setBlockStatus(false);
        this.userService.saveBlock(optionalUser.get());
        return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
    }
}
