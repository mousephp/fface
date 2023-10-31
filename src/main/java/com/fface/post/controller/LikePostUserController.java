package com.fface.post.controller;

import com.fface.base.dto.UserPrincipal;
import com.fface.base.utils.ResponseMessage;
import com.fface.chat.entity.Message;
import com.fface.manager.model.entity.UserInfo;
import com.fface.post.model.entity.LikePostUser;
import com.fface.post.model.entity.PostUser;
import com.fface.post.service.likePostUser.LikePostUserService;
import com.fface.chat.service.message.MessageService;
import com.fface.post.service.postUser.PostUserService;
import com.fface.manager.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/likePosts")
public class LikePostUserController {
    @Autowired
    private LikePostUserService likePostUserService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PostUserService postUserService;
    @Autowired
    private MessageService messageService;

   @PostMapping("/{userId}/{postUserId}")
    public ResponseEntity<LikePostUser> createNewLikePost(@PathVariable Long userId, @PathVariable Long postUserId){
        UserInfo userInfo = this.userInfoService.findByUserId(userId).get();
        PostUser postUser = this.postUserService.findById(postUserId).get();
        UserInfo toUser = postUser.getUserInfo();
        Optional<LikePostUser> like = this.likePostUserService.findLikePostUserByUserInfoIdAndPostUserId(userInfo.getId(),postUserId);
        if (!like.isPresent()){
            LikePostUser likePostUser = new LikePostUser(true,postUser,userInfo);
            this.likePostUserService.save(likePostUser);

            Message newMessage = new Message();
            newMessage.setFromUser(userInfo);
            newMessage.setToUser(toUser);
            Date date = new Date();
            newMessage.setDateCreated(date);
            newMessage.setContent("Thích bài viết của bạn!");
            newMessage.setStatus(2);
            this.messageService.save(newMessage);
            return new ResponseEntity<>(likePostUser,HttpStatus.OK);

        }else{
            this.likePostUserService.deleteById(like.get().getId());
            return new ResponseEntity<>(like.get(),HttpStatus.OK);
        }
   }
   @GetMapping("/{postUserId}")
    public ResponseEntity<Integer> countLikeOfPost(@PathVariable Long postUserId, Authentication authentication) {
       UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

//       System.out.println("user::"+user.getUsername());

       List<LikePostUser> likes = this.likePostUserService.totalLikeByPost(postUserId);
       Integer totalLikeByPost = likes.size();
       return new ResponseEntity<Integer>(totalLikeByPost,HttpStatus.OK);
   }

   /*=============================*/
   @GetMapping(value = "/{postUserId}/like")
   public ResponseEntity<?> likePost(@PathVariable(name = "postUserId") Long postUserId, Authentication authentication) {
       UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

       List<UserInfo> res = likePostUserService.likePost(user.getId(), postUserId);

       return new ResponseEntity<>(new ResponseMessage("Liked post", res),
               HttpStatus.OK);
   }

    @GetMapping(value = "{postUserId}/unlike")
    public ResponseEntity<?> unLikePost(@PathVariable(name = "postUserId") Long postUserId, Authentication authentication) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

        List<UserInfo> res = likePostUserService.unLikePost(user.getId(), postUserId);

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("UnLiked post", res),
                HttpStatus.OK);
    }
}
