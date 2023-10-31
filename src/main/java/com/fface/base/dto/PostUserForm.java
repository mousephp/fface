package com.fface.base.dto;

import com.fface.post.model.entity.StatusPost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUserForm {
    private String content;

    private MultipartFile[] image;

    private StatusPost status;
}
