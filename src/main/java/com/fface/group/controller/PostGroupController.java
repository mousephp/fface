package com.fface.group.controller;

import com.fface.chat.entity.Message;
import com.fface.base.dto.PostGroupFrontEnd;
import com.fface.base.dto.PostUserForm;
import com.fface.group.model.entity.Group;
import com.fface.group.model.entity.ImagePostGroup;
import com.fface.group.model.entity.NotificationCheckStatus;
import com.fface.group.model.entity.PostGroup;
import com.fface.group.service.group.GroupService;
import com.fface.group.service.imagePostGroup.ImagePostGroupService;
import com.fface.manager.model.entity.UserInfo;
import com.fface.chat.service.message.MessageService;
import com.fface.group.service.notificationCheckStatus.NotificationCheckStatusService;
import com.fface.post.service.postUser.PostUserService;
import com.fface.group.service.postGroup.PostGroupService;
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
@RequestMapping("/api/v1/postGroups")
public class PostGroupController {
    @Autowired
    private PostGroupService postGroupService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserInfoService userInfoService;

    @Value("/home/amidn/java/code_java/demo/gym/fface/upload/")
    private String uploadPath;

    @Autowired
    private ImagePostGroupService imagePostGroupService;

    @Autowired
    private PostUserService postUserService;

    @Autowired
    private NotificationCheckStatusService notificationCheckStatusService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/{groupId}")
    public ResponseEntity<List<PostGroupFrontEnd>> listPost(@PathVariable Long groupId) {
        List<PostGroup> postGroup = this.postGroupService.listPost(groupId);

        if (postGroup.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<PostGroupFrontEnd> listPost = new ArrayList<PostGroupFrontEnd>();

        for (int i=0;i <postGroup.size();i++){
            listPost.add(new PostGroupFrontEnd(
                    postGroup.get(i).getId(),
                    postGroup.get(i).getContent(),
                    this.imagePostGroupService.listImage(postGroup.get(i).getId()),
                    this.postUserService.getDiffDays(postGroup.get(i).getDateCreated(), new Date()),
                    postGroup.get(i).getUserInfo()
            ));
        }

        return new ResponseEntity<>(listPost, HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostGroupFrontEnd> findById(@PathVariable Long id) {
        Optional<PostGroup> postGroup = this.postGroupService.findById(id);

        if (postGroup.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        PostGroupFrontEnd postGroupFrontEnd = new PostGroupFrontEnd();

        postGroupFrontEnd.setPostGroupId(postGroup.get().getId());
        postGroupFrontEnd.setContent(postGroup.get().getContent());
        ImagePostGroup[] images = this.imagePostGroupService.listImage(postGroup.get().getId());
        postGroupFrontEnd.setListImage(images);
        postGroupFrontEnd.setDateCreated(this.postUserService.getDiffDays(postGroup.get().getDateCreated(), new Date()));
        postGroupFrontEnd.setUserInfo(postGroup.get().getUserInfo());

        return new ResponseEntity<>(postGroupFrontEnd, HttpStatus.OK);
    }

    @PostMapping("/{groupId}/{userId}")
    public ResponseEntity<PostGroup> createPostGroup(@PathVariable Long groupId, @PathVariable Long userId, @ModelAttribute PostUserForm postUserForm) {
        Group group = this.groupService.findById(groupId).get();
        MultipartFile[] listImageFile = postUserForm.getImage();
        List<String> listFileName = new ArrayList<>();
        Optional<UserInfo> userInfoOptional = this.userInfoService.findByUserId(userId);
        // lưu 1 postUser User
        PostGroup newPostGroup = new PostGroup();
        newPostGroup.setContent(postUserForm.getContent());
        newPostGroup.setDateCreated(new Date());
        newPostGroup.setGroup(group);
        newPostGroup.setUserInfo(userInfoOptional.get());
        if (userInfoOptional.get().getId() == group.getUser().getId()){
            newPostGroup.setStatusCheck(true);
            this.postGroupService.save(newPostGroup);
        }else {
            newPostGroup.setStatusCheck(false);
            this.postGroupService.save(newPostGroup);
            NotificationCheckStatus notificationCheckStatus = new NotificationCheckStatus();
            notificationCheckStatus.setAdmin(group.getUser());
            notificationCheckStatus.setPostGroup(newPostGroup);
            notificationCheckStatusService.save(notificationCheckStatus);
            Message message  = new Message();
            message.setFromUser(userInfoOptional.get());
            message.setToUser(group.getUser());
            message.setContent(userInfoOptional.get().getFullName() + " đã xin phép đăng 1 bài viết trong nhóm ");
            message.setDateCreated(new Date());
            message.setStatus(4);
            messageService.save(message);
        }
        // đổi tên ảnh sang string và add vào list tên ảnh
        if (listImageFile != null) {
            for (int i = 0; i < listImageFile.length; i++) {
                listFileName.add(listImageFile[i].getOriginalFilename());
            }
            // save ảnh vào trong database và lưu ảnh vào thư mục chứa ảnh
            for (int i = 0; i < listFileName.size(); i++) {
                this.imagePostGroupService.save(new ImagePostGroup(listFileName.get(i), newPostGroup));
                try {
                    FileCopyUtils.copy(listImageFile[i].getBytes(), new File(uploadPath + listFileName.get(i)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity<>(newPostGroup, HttpStatus.CREATED);
    }

}
