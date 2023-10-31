package com.fface.post.controller;

import com.fface.post.model.entity.StatusPost;
import com.fface.post.service.statusPost.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/status")
public class StatusPostController {
    @Autowired
    private StatusService statusService;

    @GetMapping
    public ResponseEntity<List<StatusPost>> findAll() {
        List<StatusPost> statusPostList = this.statusService.findAll();

        if (statusPostList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(statusPostList, HttpStatus.OK);
    }
}
