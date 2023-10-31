package com.fface.base.dto;

import com.fface.manager.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageFrontendDto {
    private Long id;

    private UserInfo fromUser;

    private UserInfo toUser;

    private String dateCreated;

    private String content;

    private int total;

    private int status;
}
