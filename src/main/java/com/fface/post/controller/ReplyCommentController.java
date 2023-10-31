package com.fface.post.controller;

import com.fface.base.dto.ReplyDTO;
import com.fface.post.model.entity.CommentPostUser;
import com.fface.post.model.entity.PostUser;
import com.fface.post.model.entity.ReplyComment;
import com.fface.manager.model.entity.UserInfo;
import com.fface.post.service.commentPostUser.CommentPostUserService;
import com.fface.post.service.postUser.PostUserService;
import com.fface.post.service.reply.ReplyService;
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
@RequestMapping("/reply")
@CrossOrigin("*")
public class ReplyCommentController {
    @Autowired
    private ReplyService replyService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private CommentPostUserService commentPostUserService;

    @Autowired
    private PostUserService postUserService;

    @GetMapping("/cmId")
    public ResponseEntity<List<ReplyDTO>> getAllReplyOfComment(@PathVariable Long cmId) {
        List<ReplyComment> replies = this.replyService.getAllReplyOfComment(cmId);
        List<ReplyDTO> replyDTOList = new ArrayList<>();
        for (int i = 0; i < replies.size(); i++) {
            replyDTOList.add(new ReplyDTO(replies.get(i).getId(),
                    replies.get(i).getContent(),
                    this.postUserService.getDiffDays(replies.get(i).getDateCreated(), new Date()),
                    replies.size(),
                    replies.get(i).getUserInfoComment(),
                    replies.get(i).getComment(),
                    replies.get(i).getPostUser()));
        }
        return new ResponseEntity<>(replyDTOList, HttpStatus.OK);
    }

    @PostMapping("/{fromId}/{cmId}/{postId}")
    public ResponseEntity<ReplyComment> createReplyComment(@PathVariable Long fromId, @PathVariable Long cmId, @PathVariable Long postId, @RequestBody ReplyComment replyComment) {
        UserInfo fromUser = this.userInfoService.findByUserId(fromId).get();
        PostUser postUser = this.postUserService.findById(postId).get();
        CommentPostUser comment = this.commentPostUserService.findById(cmId).get();
        ReplyComment reply = new ReplyComment();
        reply.setContent(replyComment.getContent());
        reply.setComment(comment);
        reply.setUserInfoComment(fromUser);
        reply.setDateCreated(new Date());
        reply.setPostUser(postUser);
        this.replyService.save(reply);
        return new ResponseEntity<>(reply, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReplyComment> editReply(@PathVariable Long id, @RequestBody ReplyComment replyComment) {
        Optional<ReplyComment> reply = this.replyService.findById(id);
        if (!reply.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ReplyComment newReply = reply.get();
        newReply.setId(id);
        newReply.setContent(replyComment.getContent());
        this.replyService.save(newReply);
        return new ResponseEntity<>(newReply, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReplyComment> deleteReply(@PathVariable Long id) {
        Optional<ReplyComment> reply = this.replyService.findById(id);
        if (!reply.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.replyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplyComment> findById(@PathVariable Long id) {
        Optional<ReplyComment> reply = this.replyService.findById(id);
        if (!reply.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reply.get(), HttpStatus.OK);
    }
}
