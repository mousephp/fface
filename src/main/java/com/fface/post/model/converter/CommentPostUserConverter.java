package com.fface.post.model.converter;

import com.fface.base.dto.CommentDTO;
import com.fface.post.model.dto.CommentPostUserDto;
import com.fface.post.model.dto.PostUserDto;
import com.fface.post.model.entity.CommentPostUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class CommentPostUserConverter {
    private final ModelMapper modelMapper = new ModelMapper();

    public CommentPostUserDto convertToDTO(CommentPostUser comment) {
        CommentPostUserDto commentType = modelMapper.map(comment, CommentPostUserDto.class);

        commentType.getUserInfoPost().setFullName(comment.getUserInfoPost().getFullName());
        commentType.getUserInfoPost().setEmail(comment.getUserInfoPost().getEmail());

        return commentType;
    }

    public CommentPostUser convertToEntity(CommentPostUserDto commentPostUserDto) {
        CommentPostUser comment = new CommentPostUser();
        comment.setId(commentPostUserDto.getId());
        comment.setContent(commentPostUserDto.getContent());
        comment.setPostUser(commentPostUserDto.getPostUser());

        return comment;
    }

    public CommentPostUser convertToEntity(CommentPostUser entity, CommentPostUserDto dto) {
        entity.setContent(dto.getContent());

        return entity;
    }
}
