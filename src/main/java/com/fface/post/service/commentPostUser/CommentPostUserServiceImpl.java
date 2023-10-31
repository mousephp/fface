package com.fface.post.service.commentPostUser;

import com.fface.base.exception.APIException;
import com.fface.base.exception.NotFoundException;
import com.fface.manager.model.entity.UserInfo;
import com.fface.manager.service.userInfo.UserInfoService;
import com.fface.post.model.converter.CommentPostUserConverter;
import com.fface.post.model.dto.CommentPostUserDto;
import com.fface.post.model.entity.CommentPostUser;
import com.fface.post.model.entity.PostUser;
import com.fface.post.model.entity.ReplyComment;
import com.fface.post.repository.CommentPostRepository;
import com.fface.post.repository.PostUserRepository;
import com.fface.post.service.reply.ReplyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentPostUserServiceImpl implements CommentPostUserService {
    @Autowired
    private CommentPostRepository commentPostRepository;

    @Autowired
    private PostUserRepository postUserRepository;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private UserInfoService userInfoService;

    private ModelMapper modelMapper;

    private CommentPostUserConverter commentConverter;

    public CommentPostUserServiceImpl(ModelMapper modelMapper, CommentPostUserConverter commentConverter) {
        this.modelMapper = modelMapper;
        this.commentConverter = commentConverter;
    }

    @Override
    public List<CommentPostUser> displayAllCommentOfPost(Long postUserId) {
        return commentPostRepository.displayAllCommentOfPost(postUserId);
    }

    @Override
    public Optional<CommentPostUser> findById(Long id) {
        return commentPostRepository.findById(id);
    }

    /*=======================================*/
    private CommentPostUserDto mapEntityToDto(CommentPostUser commentPostUser){
        CommentPostUserDto commentPostUserDto = modelMapper.map(commentPostUser, CommentPostUserDto.class);

        return commentPostUserDto;
    }

    private CommentPostUser mapDtoToEntity(CommentPostUserDto commentPostUserDto) {
        CommentPostUser commentPostUser = modelMapper.map(commentPostUserDto, CommentPostUser.class);

        return commentPostUser;
    }

    @Override
    public CommentPostUserDto storeComment(long userId, long postUserId, CommentPostUserDto commentDto) {
        CommentPostUser comment = commentConverter.convertToEntity(commentDto);

        UserInfo userInfo = this.userInfoService.findByUserId(userId).orElseThrow(
                () -> new NotFoundException("UserInfo", "userId", userId)
        );

        if (!postUserRepository.existsById(postUserId)) {
            throw new NotFoundException("Post not found", "postId", postUserId);
        }

        var postProxy = postUserRepository.getById(postUserId);

        comment.setPostUser(postProxy);
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        comment.setDateCreated(date);
        comment.setUserInfoPost(userInfo);

        CommentPostUser newComment = commentPostRepository.save(comment);

       return commentConverter.convertToDTO(newComment);
    }

    @Override
    public List<CommentPostUserDto> getCommentsByPostId(long postUserId) {
        List<CommentPostUser> comments = commentPostRepository.displayAllCommentOfPost(postUserId);

        return comments.stream().map(comment -> commentConverter.convertToDTO(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentPostUserDto getCommentById(Long postId, Long commentId) {
        PostUser postUser = postUserRepository.findById(postId).orElseThrow(
                () -> new NotFoundException("Post not found", "postId", postId)
        );

        CommentPostUser comment = commentPostRepository.findById(commentId).orElseThrow(
                () -> new NotFoundException("Comment not found", "commentId", commentId)
        );

        if(!comment.getPostUser().getId().equals(postUser.getId())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        
        return commentConverter.convertToDTO(comment);
    }

    @Override
    public void destroyComment(Long postUserId, Long commentId) {
        checkPostUserAndComment(postUserId, commentId);

        List<ReplyComment> replyComment = replyService.getAllRepLy(commentId);

        for (int i=0;i<replyComment.size();i++){
            this.replyService.deleteById(replyComment.get(i).getId());
        }

        commentPostRepository.deleteById(commentId);
    }

    @Override
    public CommentPostUserDto updateComment(Long postUserId, long commentId, CommentPostUserDto commentRequest) {
        checkPostUserAndComment(postUserId, commentId);

        CommentPostUser oldComment = commentPostRepository.findById(commentId).get();
        CommentPostUser commentEntity = commentConverter.convertToEntity(oldComment, commentRequest);
        CommentPostUser newComment = commentPostRepository.save(commentEntity); 

        return commentConverter.convertToDTO(newComment);
    }

    private void checkPostUserAndComment(Long postUserId, long commentId) {
        PostUser postUser = postUserRepository.findById(postUserId).orElseThrow(
                () -> new NotFoundException("Post user", "postUserId", postUserId)
        );

        CommentPostUser comment = commentPostRepository.findById(commentId).orElseThrow(
                () -> new NotFoundException("Comment", "commentId", commentId)
        );

        if(!comment.getPostUser().getId().equals(postUser.getId())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
    }
}
