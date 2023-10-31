package com.fface.post.controller;

import com.fface.post.model.entity.ImagePostUser;
import com.fface.post.service.imagePostUser.ImagePostUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/images")
public class ImagePostUserController {
    @Autowired
    private ImagePostUserService imagePostUserService;

    @GetMapping("/{imageId}")
    public ResponseEntity<ImagePostUser> findById(@PathVariable Long imageId) {
       ImagePostUser image = this.imagePostUserService.findImageById(imageId);
       return new ResponseEntity<>(image, HttpStatus.OK);
    }
    @GetMapping("/all/{postId}")
    public ResponseEntity<ImagePostUser[]> findAllImages(@PathVariable Long postId) {
       ImagePostUser[] images = this.imagePostUserService.listImage(postId);
       return new ResponseEntity<>(images, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ImagePostUser> deleteImgById(@PathVariable Long id) {
        this.imagePostUserService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
