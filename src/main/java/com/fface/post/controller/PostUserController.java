package com.fface.post.controller;

import com.fface.base.dto.*;
//import com.fface.base.service.FileStorageServiceImpl;
import com.fface.friend.entity.ListFriend;
import com.fface.manager.model.entity.UserInfo;
import com.fface.post.model.entity.CommentPostUser;
import com.fface.post.model.entity.ImagePostUser;
import com.fface.post.model.entity.PostUser;
import com.fface.post.service.commentPostUser.CommentPostUserService;
import com.fface.post.service.imagePostUser.ImagePostUserService;
import com.fface.post.service.likePostUser.LikePostUserService;
import com.fface.post.service.likecomment.LikeCommentService;
import com.fface.friend.service.listFriend.ListFriendService;
import com.fface.post.service.postUser.PostUserService;
import com.fface.post.service.reply.ReplyService;
import com.fface.manager.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/posts")
public class PostUserController {
    @Autowired
    private PostUserService postUserService;

    @Autowired
    private ImagePostUserService imagePostUser;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ImagePostUserService imagePostUserService;

    @Autowired
    private LikePostUserService likePostUserService;

    @Autowired
    private CommentPostUserService commentPostUserService;

    @Autowired
    private ListFriendService listFriendService;

    @Autowired
    private LikeCommentService likeCommentService;

    @Value("/home/amidn/java/code_java/demo/gym/fface/upload/")
    private String uploadPath;

    @Autowired
    private ReplyService replyService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<PostUserFrontEndDto>> showAllPostUser(@PathVariable Long userId) {
        Optional<UserInfo> userInfo = this.userInfoService.findByUserId(userId);
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
            listReply.add(new ListReply(this.replyService.getAllReplyOfComment(postUsers.get(i).getId())));

            postUserFrontEnd.add(new PostUserFrontEndDto(postUsers.get(i).getId(),
                    postUsers.get(i).getContent(),
                    imagePostUserService.listImage(postUsers.get(i).getId()),
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
        return new ResponseEntity<>(postUserFrontEnd, HttpStatus.OK);
    }

    @GetMapping("/friends/{userId}")
    public ResponseEntity<List<PostUserFrontEndDto>> listPostOfFriends(@PathVariable Long userId) {
        UserInfo userInfo = this.userInfoService.findByUserId(userId).get();
        List<PostUser> postUsers = this.postUserService.postUserFriends(userInfo.getId());
        List<PostUserFrontEndDto> postUserFrontEnd = new ArrayList<>();
        List<ListFriend> friends = this.listFriendService.findAll(userInfo.getId());
        List<TotalLikeCm> totalLikeCm = new ArrayList<>();
        List<CommentDTO> comments = new ArrayList<>();
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

        return new ResponseEntity<>(postUserFrontEnd, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{postUserId}")
    public ResponseEntity<PostUserFrontEndDto> findPostById(@PathVariable Long userId, @PathVariable Long postUserId) {
        Optional<PostUser> postUserOptional = this.postUserService.findById(postUserId);
        ImagePostUser[] listImage = this.imagePostUserService.listImage(postUserId);
        Optional<UserInfo> userInfo = this.userInfoService.findByUserId(userId);
        PostUserFrontEndDto postUserFrontEnd = new PostUserFrontEndDto(
                postUserId,
                postUserOptional.get().getContent(),
                listImage,
                this.postUserService.getDiffDays(postUserOptional.get().getDateCreated(),
                new Date()), userInfo.get(), postUserOptional.get().getStatus()
        );
        return new ResponseEntity<>(postUserFrontEnd, HttpStatus.OK);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<PostUser> createNewPostUser(@ModelAttribute PostUserForm postUserForm, @PathVariable Long userId) {
        MultipartFile[] listImageFile = postUserForm.getImage();
        List<String> listFileName = new ArrayList<>();
        Optional<UserInfo> userInfoOptional = this.userInfoService.findByUserId(userId);
        // lưu 1 postUser User
        PostUser newPostUser = new PostUser(postUserForm.getContent(), new Date(), postUserForm.getStatus(), userInfoOptional.get());
        this.postUserService.save(newPostUser);
        // đổi tên ảnh sang string và add vào list tên ảnh
        if (listImageFile != null) {
            for (int i = 0; i < listImageFile.length; i++) {
                listFileName.add(listImageFile[i].getOriginalFilename());
            }
            // save ảnh vào trong database và lưu ảnh vào thư mục chứa ảnh
            for (int i = 0; i < listFileName.size(); i++) {
                this.imagePostUser.save(new ImagePostUser(listFileName.get(i), newPostUser));
                try {
                    FileCopyUtils.copy(listImageFile[i].getBytes(), new File(uploadPath + listFileName.get(i)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity<>(newPostUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}/{postUserId}")
    public ResponseEntity<PostUser> editPostUser(@PathVariable Long userId, @PathVariable Long postUserId
            , @ModelAttribute PostUserForm postUserForm) {
        Optional<PostUser> postUserOptional = this.postUserService.findById(postUserId);

        PostUser newPostUser = postUserOptional.get();
        newPostUser.setId(postUserId);
        newPostUser.setContent(postUserForm.getContent());
        newPostUser.setStatus(postUserForm.getStatus());
        this.postUserService.save(newPostUser);

        MultipartFile[] listImageFile = postUserForm.getImage();
        List<String> listFileName = new ArrayList<>();
        if (listImageFile != null) {
            // đổi tên ảnh sang string và add vào list tên ảnh
            for (int i = 0; i < listImageFile.length; i++) {
                listFileName.add(listImageFile[i].getOriginalFilename());
            }
            // save ảnh vào trong database và lưu ảnh vào thư mục chứa ảnh
            for (int i = 0; i < listFileName.size(); i++) {
                this.imagePostUser.save(new ImagePostUser(listFileName.get(i), newPostUser));
                try {
                    FileCopyUtils.copy(listImageFile[i].getBytes(), new File(uploadPath + listFileName.get(i)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity<>(newPostUser, HttpStatus.OK);
    }

    @DeleteMapping("/{postUserId}")
    public ResponseEntity<PostUser> deletePost(@PathVariable Long postUserId) {
        Optional<PostUser> postUser = this.postUserService.findById(postUserId);
        if (!postUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.postUserService.deletePost(postUserId);
        return new ResponseEntity<>(postUser.get(), HttpStatus.NO_CONTENT);
    }
}
