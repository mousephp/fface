package com.fface.manager.controller;

import com.fface.base.dto.*;
import com.fface.post.model.entity.CommentPostUser;
import com.fface.post.model.entity.PostUser;
import com.fface.manager.model.entity.UserInfo;
import com.fface.post.service.commentPostUser.CommentPostUserService;
import com.fface.post.service.imagePostUser.ImagePostUserService;
import com.fface.post.service.likePostUser.LikePostUserService;
import com.fface.post.service.likecomment.LikeCommentService;
import com.fface.post.service.postUser.PostUserService;
import com.fface.post.service.reply.ReplyService;
import com.fface.manager.service.user.UserService;
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
@RequestMapping("/userDetails")
public class UserDetailsController {
    @Autowired
    private PostUserService postUserService;

    @Autowired
    private ImagePostUserService imagePostUser;

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ImagePostUserService imagePostUserService;

    @Autowired
    private LikePostUserService likePostUserService;

    @Autowired
    private CommentPostUserService commentPostUserService;
    @Autowired
    private LikeCommentService likeCommentService;
    @Autowired
    private ReplyService replyService;

    @GetMapping("/{userInfoId}")
    public ResponseEntity<UserDetails> showAllPostUser(@PathVariable Long userInfoId) {
        Optional<UserInfo> userInfo = this.userInfoService.findById(userInfoId);
        List<PostUser> postUsers = this.postUserService.showAllPostByUser(userInfo.get().getId());
        List<PostUserFrontEndDto> postUserFrontEnd = new ArrayList<>();
        List<CommentDTO> comments = new ArrayList<>();
        List<TotalLikeCm> totalLikeCm = new ArrayList<>();
        List<ListReply> listReply = new ArrayList<>();

        for (int i = 0; i < postUsers.size(); i++) {
            List<CommentPostUser> listCm = this.commentPostUserService.displayAllCommentOfPost(postUsers.get(i).getId());
            for (int j = 0; j < listCm.size(); j++) {
                totalLikeCm.add(new TotalLikeCm(listCm.get(j).getId()
                        , this.likeCommentService.listLikeComments(listCm.get(j).getId()).size()));
            }
            for (int j = 0; j < listCm.size(); j++) {
                comments.add(new CommentDTO(listCm.get(j).getId(),
                        listCm.get(j).getContent(),
                        this.postUserService.getDiffDays(listCm.get(j).getDateCreated(), new Date()),
                        listCm.get(j).getUserInfoPost(),
                        listCm.get(j).getPostUser()));
            }
            postUserFrontEnd.add(new PostUserFrontEndDto(postUsers.get(i).getId(), postUsers.get(i).getContent(), imagePostUserService.listImage(postUsers.get(i).getId()),
                    this.postUserService.getDiffDays(postUsers.get(i).getDateCreated(), new Date())
                    , postUsers.get(i).getUserInfo(), likePostUserService.totalLikeByPost(postUsers.get(i).getId()).size(),
                    this.commentPostUserService.displayAllCommentOfPost(postUsers.get(i).getId()),
                    commentPostUserService.displayAllCommentOfPost(postUsers.get(i).getId()).size(),
                    postUsers.get(i).getStatus(),
                    totalLikeCm,
                    this.replyService.getAllReplyOfComment(postUsers.get(i).getId()),
                    this.replyService.getAllReplyOfComment(postUsers.get(i).getId()).size()
            ));
        }
        UserDetails userDetails = new UserDetails(userInfo.get(), postUserFrontEnd);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }
}
