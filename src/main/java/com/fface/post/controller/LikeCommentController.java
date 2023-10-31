package com.fface.post.controller;

import com.fface.post.model.entity.CommentPostUser;
import com.fface.post.model.entity.LikeComment;
import com.fface.chat.entity.Message;
import com.fface.manager.model.entity.UserInfo;
import com.fface.post.service.commentPostUser.CommentPostUserService;
import com.fface.post.service.likePostUser.LikePostUserService;
import com.fface.post.service.likecomment.LikeCommentService;
import com.fface.chat.service.message.MessageService;
import com.fface.post.service.postUser.PostUserService;
import com.fface.manager.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/likeComments")
public class LikeCommentController {
    @Autowired
    private LikePostUserService likePostUserService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PostUserService postUserService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private LikeCommentService likeCommentService;
    @Autowired
    private CommentPostUserService commentPostUserService;

    @PostMapping("/{commentId}/{userId}")
    public ResponseEntity<LikeComment> createLikeComment(@PathVariable Long commentId, @PathVariable Long userId){
        UserInfo fromUser = this.userInfoService.findByUserId(userId).get();
        CommentPostUser commentPostUser = this.commentPostUserService.findById(commentId).get();
        Optional<LikeComment> likeComment = this.likeCommentService.findLikeCommentByUser(commentId,fromUser.getId());
        UserInfo toUser = commentPostUser.getUserInfoPost();
        if (!likeComment.isPresent()) {
            LikeComment newLikeComment = new LikeComment(fromUser,commentPostUser,0);
            this.likeCommentService.save(newLikeComment);

            Message newMessage = new Message();
            newMessage.setFromUser(fromUser);
            newMessage.setToUser(toUser);
            Date date = new Date();
            newMessage.setDateCreated(date);
            newMessage.setContent("Thích bình luận của bạn!");
            newMessage.setStatus(4);
            this.messageService.save(newMessage);
            return new ResponseEntity<>(newLikeComment, HttpStatus.OK);
        }else{
            this.likeCommentService.deleteById(likeComment.get().getId());
            return new ResponseEntity<>(likeComment.get(),HttpStatus.OK);
        }
    }
    @GetMapping("/{cmPostId}")
    public ResponseEntity<Integer> totalLikeCm(@PathVariable Long cmPostId){
        CommentPostUser commentPostUser = this.commentPostUserService.findById(cmPostId).get();
        List<LikeComment> listLikeComments = this.likeCommentService.listLikeComments(cmPostId);
        return new ResponseEntity<>(listLikeComments.size(), HttpStatus.OK);
    }
}
