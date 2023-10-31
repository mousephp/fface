package com.fface.base.dto;

import com.fface.manager.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private Long id;

    private UserInfo user1;

    private UserInfo user2;

    private String lastContent;

    private String dateCreated;
}
