package com.fface.post.controller;

import com.fface.base.dto.UserPrincipal;
import com.fface.base.utils.ResponseMessage;
import com.fface.manager.service.userInfo.UserInfoService;
import com.fface.post.model.entity.PostUser;
import com.fface.post.service.postUser.PostUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SharePostController {
    @Autowired
    private PostUserService postUserService;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/posts/{postUserId}/share")
    public ResponseEntity<?> sharePost(@PathVariable(name = "postUserId") Long postUserId) { //, Authentication authentication
//        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        List<PostUser> res = postUserService.sharePost(1L, postUserId);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Liked post", res),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/posts/{postUserId}/removeShare")
    public ResponseEntity<?> removeSharePost(@PathVariable(name = "postUserId") Long postUserId,
                                             Authentication authentication) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        List<PostUser> res = postUserService.removeSharePost(user.getId(), postUserId);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage("Liked post", res),
                HttpStatus.OK);
    }
}
