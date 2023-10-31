package com.fface.manager.controller;

import com.fface.base.dto.AvatarForm;
import com.fface.base.dto.BackgroundForm;
import com.fface.base.dto.UserInfoExitDTO;
import com.fface.post.model.entity.ImagePostUser;
import com.fface.post.model.entity.PostUser;
import com.fface.manager.model.entity.UserInfo;
import com.fface.post.service.imagePostUser.ImagePostUserService;
import com.fface.post.service.postUser.PostUserService;
import com.fface.manager.service.userInfo.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/userInfo")
public class    UserInfoRestController {
//     @Value("${file-upload}")
     @Value("/home/amidn/java/code_java/demo/gym/fface/upload/")
     String uploadPath;

    @Autowired
    private PostUserService postUserService;

    @Autowired
    private ImagePostUserService imageService;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping()
    public ResponseEntity<Page<UserInfo>> showAllUser(@PageableDefault(value = 10) Pageable pageable){
        Page<UserInfo> users = this.userInfoService.findAll(pageable);
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @GetMapping("/searchByFullName/{fullName}")
    public ResponseEntity<List<UserInfo>> searchByFullName(@PathVariable String fullName) {
        List<UserInfo> users = this.userInfoService.findByFullNameContaining(fullName);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<UserInfoExitDTO> emailExitCheck(@PathVariable String email){
        List<UserInfo> users = this.userInfoService.findAllUser();
        UserInfoExitDTO emailDTO = new UserInfoExitDTO();
        emailDTO.setStatus(false);
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getEmail().equals(email)){
                emailDTO.setStatus(true);
                break;
            }
        }
        return new ResponseEntity<>(emailDTO,HttpStatus.OK);
    }
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<UserInfoExitDTO> phoneExitCheck(@PathVariable String phoneNumber){
        List<UserInfo> users = this.userInfoService.findAllUser();
        UserInfoExitDTO userInfoExitDTO = new UserInfoExitDTO();
        userInfoExitDTO.setStatus(false);
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getPhoneNumber().equals(phoneNumber)){
                userInfoExitDTO.setStatus(true);
                break;
            }
        }
        return new ResponseEntity<>(userInfoExitDTO,HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<UserInfo>> findAllUser(@PathVariable long id) {
        List<UserInfo> users = this.userInfoService.showAllUserInfo(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    //Tìm cụ thể thông tin của 1 user
    @GetMapping("/{userId}")
    public ResponseEntity<UserInfo> findById(@PathVariable Long userId){
        Optional<UserInfo> userInfo = this.userInfoService.findByUserId(userId);
        if (!userInfo.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userInfo.get(), HttpStatus.OK);
    }

//    update thông tin cảu 1 user
    @PutMapping("/updateUserInfo/{userId}")
    public ResponseEntity<UserInfo> updateUserInfo(@PathVariable Long userId, @RequestBody UserInfo userInfo) {
        Optional<UserInfo> userInfoOptinal = userInfoService.findByUserId(userId);
        if (!userInfoOptinal.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserInfo newUserInfo = userInfoOptinal.get();
        newUserInfo.setId(newUserInfo.getId());
        newUserInfo.setAddress(userInfo.getAddress());
        newUserInfo.setInterest(userInfo.getInterest());
        newUserInfo.setFullName(userInfo.getFullName());
        newUserInfo.setPhoneNumber(userInfo.getPhoneNumber());
        return new ResponseEntity<>(userInfoService.save(newUserInfo),HttpStatus.OK);
    }

    @PutMapping ("/avt/{userId}")
    public ResponseEntity<UserInfo> editAvt(@PathVariable Long userId, @ModelAttribute AvatarForm avatarForm) {
        Optional<UserInfo> userInfo = userInfoService.findByUserId(userId);

        if (!userInfo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MultipartFile multipartFile = avatarForm.getAvatar();
        String image;

            String fileName = multipartFile.getOriginalFilename();
            long crt = System.currentTimeMillis();
            fileName =crt+fileName;
            image = fileName;
            try {
                FileCopyUtils.copy(avatarForm.getAvatar().getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

        PostUser newPost = new PostUser();
        newPost.setContent(userInfo.get().getFullName() + " đã thay đổi ảnh đại diện");
        newPost.setStatus(avatarForm.getStatus());
        newPost.setDateCreated(new Date());
        newPost.setUserInfo(userInfo.get());
        this.postUserService.save(newPost);
        this.imageService.save(new ImagePostUser(image, newPost));
        UserInfo userInfo1 = new UserInfo(userInfo.get().getId(), userInfo.get().getEmail(), userInfo.get().getFullName(), userInfo.get().getPhoneNumber(), userInfo.get().getDateOfBirth(),userInfo.get().getAddress(),image, userInfo.get().getBackGround(), userInfo.get().getUser());
        return new ResponseEntity<>(userInfoService.save(userInfo1), HttpStatus.OK);
    }


    @PutMapping ("/bgr/{userId}")
    public ResponseEntity<UserInfo> editBgr(@PathVariable Long userId, @ModelAttribute BackgroundForm backgroundForm) {
        Optional<UserInfo> userInfo = userInfoService.findByUserId(userId);
        if (!userInfo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MultipartFile multipartFile = backgroundForm.getBackground();
        String image;
        String fileName = multipartFile.getOriginalFilename();
            long crt = System.currentTimeMillis();
            fileName =fileName + crt;
            image = fileName;
            try {
                FileCopyUtils.copy(backgroundForm.getBackground().getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        PostUser newPost = new PostUser();
        newPost.setContent(userInfo.get().getFullName() + " đã thay đổi ảnh nền");
        newPost.setStatus(backgroundForm.getStatus());
        newPost.setDateCreated(new Date());
        newPost.setUserInfo(userInfo.get());
        this.postUserService.save(newPost);
        this.imageService.save(new ImagePostUser(image, newPost));
        UserInfo userInfo1 = new UserInfo(userInfo.get().getId(), userInfo.get().getEmail(), userInfo.get().getFullName(), userInfo.get().getPhoneNumber(), userInfo.get().getDateOfBirth(),userInfo.get().getAddress(),userInfo.get().getAvatar(), image, userInfo.get().getUser());
        return new ResponseEntity<>(userInfoService.save(userInfo1), HttpStatus.OK);
    }

//    update userInfo
    @PutMapping ("/userInfo/{userId}")
    public ResponseEntity<UserInfo> updateUserInfo(@PathVariable Long userId, @ModelAttribute BackgroundForm backgroundForm) {
        Optional<UserInfo> userInfo = userInfoService.findByUserId(userId);
        if (!userInfo.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MultipartFile multipartFile = backgroundForm.getBackground();
        String image;
        String fileName = multipartFile.getOriginalFilename();
            long crt = System.currentTimeMillis();
            fileName =fileName + crt;
            image = fileName;
            try {
                FileCopyUtils.copy(backgroundForm.getBackground().getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        PostUser newPost = new PostUser();
        newPost.setContent(userInfo.get().getFullName() + " đã thay đổi ảnh nền");
        newPost.setStatus(backgroundForm.getStatus());
        newPost.setDateCreated(new Date());
        newPost.setUserInfo(userInfo.get());
        this.postUserService.save(newPost);
        this.imageService.save(new ImagePostUser(image, newPost));
        UserInfo userInfo1 = new UserInfo(userInfo.get().getId(), userInfo.get().getEmail(), userInfo.get().getFullName(), userInfo.get().getPhoneNumber(), userInfo.get().getDateOfBirth(),userInfo.get().getAddress(),userInfo.get().getAvatar(), image, userInfo.get().getUser());
        return new ResponseEntity<>(userInfoService.save(userInfo1), HttpStatus.OK);
    }

}
