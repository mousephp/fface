package com.fface.group.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SetManager {
    private Long groupId;
    private Long userInfoId;
    private String role;
}