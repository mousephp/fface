package com.fface.base.dto;

import com.fface.group.model.entity.ImagePostGroup;
import com.fface.manager.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostGroupFrontEnd {
    private Long postGroupId;

    private String content;

    private ImagePostGroup[] listImage;

    private String dateCreated;

    private UserInfo userInfo;
}
