package com.fface.post.controller;

import com.fface.post.model.dto.CommentPostUserDto;
import com.fface.post.service.commentPostUser.CommentPostUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class CommentPostUserController {
        @Autowired
        private CommentPostUserService commentPostUserService;

        @PostMapping("/posts/{userId}/{postUserId}")
        public ResponseEntity<CommentPostUserDto> storeComment(@PathVariable long userId,
                                                               @PathVariable(value = "postUserId") long postUserId,
                                                               @RequestBody CommentPostUserDto commentDto){
                return new ResponseEntity<>(commentPostUserService.storeComment(userId, postUserId, commentDto), HttpStatus.OK);
        }

        @GetMapping("/posts/{postUserId}/comments")
        public ResponseEntity<List<CommentPostUserDto>> getCommentsByPostId(@PathVariable("postUserId") long postUserId) {
                return new ResponseEntity<>(commentPostUserService.getCommentsByPostId(postUserId), HttpStatus.OK);
        }

        @GetMapping("/posts/{postUserId}/comments/{commentId}")
        public ResponseEntity<CommentPostUserDto> getCommentById(@PathVariable("postUserId") Long postUserId,
                                                                 @PathVariable("commentId") Long commentId) {
                return new ResponseEntity<>(commentPostUserService.getCommentById(postUserId, commentId), HttpStatus.OK);
        }

        @PutMapping("/posts/{postUserId}/comments/{commentId}")
        public ResponseEntity<CommentPostUserDto> updateComment(@PathVariable("postUserId") Long postUserId,
                                                                @PathVariable("commentId") long commentId,
                                                                @RequestBody CommentPostUserDto commentRequest) {
                return new ResponseEntity<>(commentPostUserService.updateComment(postUserId, commentId, commentRequest), HttpStatus.OK);
        }

        @DeleteMapping("/posts/{postUserId}/comments/{commentId}")
        public ResponseEntity<String>destroyComment(@PathVariable("postUserId") Long postUserId,
                                                    @PathVariable("commentId") Long commentId){
                commentPostUserService.destroyComment(postUserId, commentId);

                return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
        }

}
