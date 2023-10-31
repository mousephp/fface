package com.fface.group.model.dto;

import com.fface.group.model.entity.GroupStatus;
import com.fface.manager.model.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GroupDto
{
    private Long id;

    private String name;

    private String avatar;

    private String background;

    private UserInfo user;

    private GroupStatus groupStatus;
}
