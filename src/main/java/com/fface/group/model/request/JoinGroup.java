package com.fface.group.model.request;

import lombok.Data;

@Data
public class JoinGroup {
    private Long userInfoId;
    private Long groupId;
}